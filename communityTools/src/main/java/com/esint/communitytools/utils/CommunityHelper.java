package com.esint.communitytools.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.conn.ConnectionReleaseTrigger;

import com.esint.communitytools.bean.CAccount;
import com.esint.communitytools.bean.CCompanyUser;
import com.esint.communitytools.bean.CService;
import com.esint.communitytools.bean.ServiceApp;
import com.esint.communitytools.data.CoreData;
import com.esint.communitytools.data.PropertyKey;
import com.esint.communitytools.data.URIMap;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * 账号帮助类
 * 
 * @author mx
 *
 */
public class CommunityHelper {
	private static final String LOG = "CommunityHelper";
	// 秘钥
	private String codeKey;
	private static CommunityHelper mAccountHelper;
	private static Context mContext;
	private ContentResolver mContentResolver;
	/**
	 * 账户信息uri
	 */
	private String baseURI = "content://";
	private String accountURI = "content://";
	private String propertyURI = "content://";
	private String serviceURI = "content://";
	/*
	 * 2017-06-01 添加企业账号信息
	 */
	private String companyURI = "content://";
	private CommunityHelper(Context context) {
		mContext = context;
		mContentResolver = context.getContentResolver();
		try {
			ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(),
					PackageManager.GET_META_DATA);
			byte[] encryptedAuthor = Base64Utils.decode(info.metaData.getString(CoreData.META_APP_AUTHOR));
			InputStream is = context.getResources().getAssets().open("pkcs8_rsa_private_key.pem");
			PrivateKey privateKey = RSAUtils.loadPrivateKey(is);
			String author = new String(RSAUtils.decryptData(encryptedAuthor, privateKey));
			Log.d(LOG, author);
			baseURI = baseURI + author + "/";
			accountURI = accountURI + author + "/" + CoreData.URI_ACCOUNT;
			propertyURI = propertyURI + author + "/" + CoreData.URI_PROPERTY;
			serviceURI = serviceURI + author + "/" + CoreData.URI_SERVICE;
			/*
			 * 2017-06-01 添加企业账号信息
			 */
			companyURI = companyURI + author + "/" + CoreData.URI_COMPANY_USER;
			
			codeKey = info.metaData.getString(CoreData.META_APP_KEY);
		} catch (NameNotFoundException e) {
			Log.e(LOG, "not found mate_value of main app author or packagename");
			mAccountHelper = null;
		} catch (IOException io) {
			Log.e(LOG, "not found pkcs8_rsa_private_key.pem");
			mAccountHelper = null;
		} catch (Exception RSA) {
			Log.e(LOG, "RAS ERROR");
			mAccountHelper = null;
		}
	}

	public static CommunityHelper getInstance(Context context) {
		if (null == mAccountHelper) {
			mAccountHelper = new CommunityHelper(context);
		}
		return mAccountHelper;
	}

	/**
	 * 判断是否有已登录的账号
	 * 
	 * @return 账号登录则返回账号信息 没有则返回null
	 */
	public CAccount getLoginedAccount() {
		CAccount account = null;
		Uri uri = Uri.parse(accountURI);
		Cursor cursor = null;
		cursor = mContentResolver.query(uri, null, CoreData.ACCOUNT_ACTIVITE + "=?", new String[] { "1" }, null);
		if (null != cursor) {
			if (cursor.moveToFirst()) {
				account = new CAccount();
				account.setUserid(cursor.getInt(cursor.getColumnIndex(CoreData.ACCOUNT_USERID)));
				account.setUsername(cursor.getString(cursor.getColumnIndex(CoreData.ACCOUNT_USER)));
				account.setPassword(cursor.getString(cursor.getColumnIndex(CoreData.ACCOUNT_PASS)));
				account.setEmail(cursor.getString(cursor.getColumnIndex(CoreData.ACCOUNT_EMAIL)));
				account.setCardId(cursor.getString(cursor.getColumnIndex(CoreData.ACCOUNT_CARDID)));
				account.setSex(cursor.getInt(cursor.getColumnIndex(CoreData.ACCOUNT_SEX)));
				account.setTelphone(cursor.getString(cursor.getColumnIndex(CoreData.ACCOUNT_TEL)));
				account.setActivite(cursor.getInt(cursor.getColumnIndex(CoreData.ACCOUNT_ACTIVITE)));
			}
			cursor.close();
		}
		return account;
	}

	/**
	 * 登录成功记录登录信息
	 * 
	 * @param account
	 * @return 返回是否添加成功
	 */
	public boolean addLoginAccount(CAccount account) {
		Uri uri = Uri.parse(baseURI + URIMap.ACCOUNT_ADDUSER);
		ContentValues values = new ContentValues();
		values.put(CoreData.ACCOUNT_USERID, account.getUserid());
		values.put(CoreData.ACCOUNT_USER, account.getUsername());
		values.put(CoreData.ACCOUNT_PASS, account.getPassword());
		values.put(CoreData.ACCOUNT_SEX, account.getSex());
		values.put(CoreData.ACCOUNT_EMAIL, account.getEmail());
		values.put(CoreData.ACCOUNT_CARDID, account.getCardId());
		values.put(CoreData.ACCOUNT_TEL, account.getTelphone());
		values.put(CoreData.ACCOUNT_ACTIVITE, "1");
		return mContentResolver.insert(uri, values) != null;
	}

	/**
	 * 根据用户名进行用户登出操作
	 * 
	 * @param username
	 *            用户名
	 * @return 登出是否成功
	 */
	public boolean userLogout(String username) {
		boolean isLogouted = false;
		if (null == username) {
			return isLogouted;
		}
		Uri uri = Uri.parse(baseURI + URIMap.ACCOUNT_USERLOGOUTWITHNAME);
		ContentValues values = new ContentValues();
		values.put(CoreData.ACCOUNT_USER, username);
		isLogouted = mContentResolver.update(uri, values, null, null) != 0;
		return isLogouted;
	}

	/**
	 * 用户登出
	 * 
	 * @return
	 */
	public boolean userLogout() {
		boolean isLogouted = false;
		Uri uri = Uri.parse(baseURI + URIMap.ACCOUNT_USERLOGOUT);
		ContentValues values = new ContentValues();
		isLogouted = mContentResolver.update(uri, values, null, null) != 0;
		return isLogouted;
	}

	/**
	 * 添加运营商绑定码及其信息
	 * 
	 * @param serviceCode
	 *            运营商
	 * @return
	 */
	public boolean addServiceInfo(CService service) {
		boolean isInsert = false;
		Uri uri = Uri.parse(baseURI + URIMap.PROPERTY_ADDSERVICECODE);
		ContentValues propertyValues = new ContentValues();
		propertyValues.put(PropertyKey.PROPERTY_SERVICECODE, service.getServiceCode());
		propertyValues.put(PropertyKey.PROPERTY_SERVICENAME, service.getServiceName());
		propertyValues.put(PropertyKey.PROPERTY_SERVICEICON, service.getServiceIcon());
		boolean isApp = true;
		Uri appUri = Uri.parse(baseURI + URIMap.SERVICE_ADDSERVICEAPP);
		List<ServiceApp> apps = service.getServiceApps();
		if (apps != null) {
			ContentValues[] values = new ContentValues[apps.size()];
			for (int i = 0; i < apps.size(); i++) {
				ServiceApp serviceApp = apps.get(i);
				ContentValues appValues = new ContentValues();
				appValues.put(CoreData.SERVICE_APPNAME, serviceApp.getAppname());
				appValues.put(CoreData.SERVICE_APPTYPE, serviceApp.getApptype());
				appValues.put(CoreData.SERVICE_URL, serviceApp.getUrl());
				appValues.put(CoreData.SERVICE_DISPLAYNAME, serviceApp.getDisplayname());
				values[i] = appValues;
			}
			mContentResolver.bulkInsert(appUri, values);
		}
		isInsert = mContentResolver.insert(uri, propertyValues) != null && isApp;
		return isInsert;
	}

	/**
	 * 解绑运营商
	 * 
	 * @return
	 */
	public boolean unBindService() {
		boolean isSuc = false;
		Uri proUri = Uri.parse(baseURI + URIMap.PROPERTY_DELETESERVICE);
		boolean isPro = mContentResolver.delete(proUri, null, null) > 0;
		Uri serUri = Uri.parse(baseURI + URIMap.SERVICE_DELETEAPP);
		boolean isSer = mContentResolver.delete(serUri, null, null) > 0;
		isSuc = isPro && isSer;
		return isSuc;
	}

	/**
	 * 获得运营商信息
	 * 
	 * @return 返回运营商信息 没有则返回null
	 */
	public CService getServiceInfo() {
		CService service = new CService();
		ServiceApp serviceApp = null;
		Uri proUri = Uri.parse(propertyURI);
		Cursor proCursor = null;
		proCursor = mContentResolver.query(proUri, null, null, null, null);
		if (null != proCursor) {
			while (proCursor.moveToNext()) {
				if (proCursor.getString(proCursor.getColumnIndex(CoreData.PROPERTY_KEY))
						.equals(PropertyKey.PROPERTY_SERVICECODE)) {
					// 绑定码
					service.setServiceCode(proCursor.getString(proCursor.getColumnIndex(CoreData.PROPERTY_VALUE)));
				} else if (proCursor.getString(proCursor.getColumnIndex(CoreData.PROPERTY_KEY))
						.equals(PropertyKey.PROPERTY_SERVICENAME)) {
					// 运营商名
					service.setServiceName(proCursor.getString(proCursor.getColumnIndex(CoreData.PROPERTY_VALUE)));
				} else if (proCursor.getString(proCursor.getColumnIndex(CoreData.PROPERTY_KEY))
						.equals(PropertyKey.PROPERTY_SERVICEICON)) {
					// 运营商图标
					service.setServiceIcon(proCursor.getString(proCursor.getColumnIndex(CoreData.PROPERTY_VALUE)));
				}
			}
			proCursor.close();
		}
		Uri serviceUri = Uri.parse(serviceURI);
		Cursor serviceCursor = null;
		serviceCursor = mContentResolver.query(serviceUri, null, null, null, null);
		if (null != serviceCursor) {
			List<ServiceApp> apps = new ArrayList<ServiceApp>();
			while (serviceCursor.moveToNext()) {
				serviceApp = new ServiceApp();
				serviceApp.setAppname(serviceCursor.getString(serviceCursor.getColumnIndex(CoreData.SERVICE_APPNAME)));
				serviceApp.setApptype(serviceCursor.getInt(serviceCursor.getColumnIndex(CoreData.SERVICE_APPTYPE)));
				serviceApp.setUrl(serviceCursor.getString(serviceCursor.getColumnIndex(CoreData.SERVICE_URL)));
				serviceApp.setDisplayname(
						serviceCursor.getString(serviceCursor.getColumnIndex(CoreData.SERVICE_DISPLAYNAME)));
				apps.add(serviceApp);
			}
			service.setServiceApps(apps);
			serviceCursor.close();
		}
		if (service.getServiceCode() == null
				|| (service.getServiceCode() != null && service.getServiceCode().equals(""))) {
			return null;
		}
		return service;
	}

	/**
	 * 获得当前服务绑定码
	 * 
	 * @return 没有返回null
	 */
	public String getServiceCode() {
		String code = null;
		Uri uri = Uri.parse(propertyURI);
		Cursor cursor = null;
		cursor = mContentResolver.query(uri, null, CoreData.PROPERTY_KEY + "=?",
				new String[] { PropertyKey.PROPERTY_SERVICECODE }, null);
		if (null != cursor) {
			if (cursor.moveToFirst()) {
				code = cursor.getString(cursor.getColumnIndex(CoreData.PROPERTY_VALUE));
			}
			cursor.close();
		}
		return code;
	}

	/**
	 * 获得应用类别服务地址
	 * 
	 * @param appType
	 *            应用类别 AppType常量
	 * @return 运营商该app服务地址 没有返回null
	 */
	public String getAppServiceUrl(int appType) {
		String url = null;
		Uri uri = Uri.parse(serviceURI);
		Cursor cursor = null;
		cursor = mContentResolver.query(uri, null, CoreData.SERVICE_APPTYPE + "=?", new String[] { appType + "" },
				null);
		if (null != cursor) {
			if (cursor.moveToFirst()) {
				url = cursor.getString(cursor.getColumnIndex(CoreData.SERVICE_URL));
			}
		}
		return url;
	}

	/**
	 * 获得应用类别运营商信息
	 * 
	 * @param appType
	 *            应用类别 AppType常量
	 * @return 运营商该app服务地址 没有返回null
	 */
	public ServiceApp getAppService(int appType) {
		ServiceApp serviceApp = null;
		Uri uri = Uri.parse(serviceURI);
		Cursor cursor = null;
		cursor = mContentResolver.query(uri, null, CoreData.SERVICE_APPTYPE + "=?", new String[] { appType + "" },
				null);
		if (null != cursor) {
			if (cursor.moveToFirst()) {
				serviceApp = new ServiceApp();
				serviceApp.setAppname(cursor.getString(cursor.getColumnIndex(CoreData.SERVICE_APPNAME)));
				serviceApp.setUrl(cursor.getString(cursor.getColumnIndex(CoreData.SERVICE_URL)));
				serviceApp.setApptype(cursor.getInt(cursor.getColumnIndex(CoreData.SERVICE_APPTYPE)));
				serviceApp.setDisplayname(cursor.getString(cursor.getColumnIndex(CoreData.SERVICE_DISPLAYNAME)));
			}
		}
		return serviceApp;
	}

	/**
	 * 跳转程序
	 * 
	 * @param packagename
	 * @return
	 */
	public int exitToHome() {
		String packagename = "com.esint.communityprovide_homepad";
		// 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
		PackageInfo packageinfo = null;
		try {
			packageinfo = mContext.getPackageManager().getPackageInfo(packagename, 0);
		} catch (NameNotFoundException e) {
			Log.e(LOG, "sendToApp NameNotFoundException : " + (e == null ? "" : e.getMessage()));
			return 0;
		}
		if (packageinfo == null) {
			return 0;
		}
		// // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
		// Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
		// resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		// resolveIntent.setPackage(packageinfo.packageName);
		//
		// // 通过getPackageManager()的queryIntentActivities方法遍历
		// List<ResolveInfo> resolveinfoList =
		// mContext.getPackageManager().queryIntentActivities(resolveIntent, 0);
		//
		// ResolveInfo resolveinfo = resolveinfoList.iterator().next();
		// if (resolveinfo != null) {
		// // packagename = 参数packname
		// String packageName = resolveinfo.activityInfo.packageName;
		// // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
		// String className = resolveinfo.activityInfo.name;
		// // LAUNCHER Intent
		// Intent intent = new Intent(Intent.ACTION_MAIN);
		// intent.addCategory(Intent.CATEGORY_LAUNCHER);
		// // 设置ComponentName参数1:packagename参数2:MainActivity路径
		// ComponentName cn = new ComponentName(packageName, className);
		// intent.setComponent(cn);
		// mContext.startActivity(intent);
		// }
		PackageManager packageManager = mContext.getPackageManager();
		Intent intent = packageManager.getLaunchIntentForPackage(packagename);
		mContext.startActivity(intent);
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
		return 1;
	}
	
	/**
	 * 判断是否有已登录的账号
	 * 
	 * @return 账号登录则返回账号信息 没有则返回null
	 */
	public CCompanyUser getLoginedCompanyUser() {
		CCompanyUser companyUser = null;
		Uri uri = Uri.parse(companyURI);
		Cursor cursor = null;
		cursor = mContentResolver.query(uri, null, CoreData.COMPANY_USER_STATUS + "=?", new String[] { "1" }, null);
		if (null != cursor) {
			if (cursor.moveToFirst()) {
				companyUser = new CCompanyUser();
				companyUser.setUsername(cursor.getString(cursor.getColumnIndex(CoreData.COMPANY_USER_USER)));
				companyUser.setBindCode(cursor.getString(cursor.getColumnIndex(CoreData.COMPANY_USER_BINDCODE)));
				companyUser.setLoginStutas(cursor.getInt(cursor.getColumnIndex(CoreData.COMPANY_USER_STATUS)));
				companyUser.setPassword(cursor.getString(cursor.getColumnIndex(CoreData.COMPANY_USER_PASSWORD)));
				companyUser.setId(cursor.getInt(cursor.getColumnIndex(CoreData.COMPANY_USER_ID)));
			}
			cursor.close();
		}
		return companyUser;
	}

}
