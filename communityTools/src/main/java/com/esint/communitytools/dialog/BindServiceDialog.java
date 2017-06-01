package com.esint.communitytools.dialog;

import java.util.HashMap;
import java.util.Map;

import com.esint.communitytools.R;
import com.esint.communitytools.bean.CService;
import com.esint.communitytools.bean.JsonBindCode;
import com.esint.communitytools.bean.ServiceApp;
import com.esint.communitytools.bean.ServiceMessage;
import com.esint.communitytools.data.ResultCode;
import com.esint.communitytools.utils.CommunityHelper;
import com.esint.communitytools.utils.JsonUtils;
import com.esint.communitytools.utils.WebConstants;
import com.esint.communitytools.utils.WebVolleyUtils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * 绑定运营商
 * 
 * @author Administrator
 *
 */
public class BindServiceDialog extends ToolsBaseDialog {
	private final String TAG = "BindServiceDialog";
	private String bindCode;
	private Dialog dialog;
	// app种类
	private int appType;
	private BindClickListener mListener;
	private BindCallBack mCallback;
	private EditText codeEditText;
	private Button bindButton, cancelButton;
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case WebConstants.WEBFLAG_ERROR:
				if (null != mLoadingDialog && mLoadingDialog.isShowing())
					mLoadingDialog.dismiss();
				if (null != mCallback) {
					// mCallback.bindService(isBinded, url);
				}
				break;

			case WebConstants.WEBFLAG_BIND:
				if (null != mLoadingDialog && mLoadingDialog.isShowing())
					mLoadingDialog.dismiss();
				if (null != mCallback) {
					JsonBindCode bindCodeJson = JsonUtils.getInstance().getBindService(msg.obj.toString());
					if (null != bindCodeJson) {
						int resultCode = bindCodeJson.getCode();
						switch (resultCode) {
						case WebConstants.RESULTCODE_SUC:
							// 绑定成功
							ServiceMessage serviceMsg = bindCodeJson.getContent();
							if (null != serviceMsg && null != serviceMsg.getManagers()) {
								CService service = new CService();
								service.setServiceName(serviceMsg.getName());
								service.setServiceCode(bindCode);
								service.setServiceIcon(serviceMsg.getIcon());
								for (ServiceApp app : serviceMsg.getManagers()) {
									Log.d(TAG, "apptype: " + app.getApptype());
								}
								service.setServiceApps(serviceMsg.getManagers());
								boolean isAdded = CommunityHelper.getInstance(mContext).addServiceInfo(service);
								if (isAdded) {
									if (appType != 0)
										mCallback.bindService(ResultCode.RESULT_SUCCESS, "");
									else {
										String url = CommunityHelper.getInstance(mContext).getAppServiceUrl(appType);
										mCallback.bindService(ResultCode.RESULT_SUCCESS, url);
									}
								} else
									mCallback.bindService(ResultCode.RESULT_FAIL, "数据库添加错误 ");
							} else {
								mCallback.bindService(ResultCode.RESULT_FAIL, "网络数据返回错误");
							}
							break;
						case ResultCode.RESULT_FAIL:
							// 错误
							mCallback.bindService(ResultCode.RESULT_FAIL, bindCodeJson.getError());
							break;
						}
					}
				}
				dialog.dismiss();
				break;
			}
		}

	};

	/**
	 * 
	 * @param context
	 *            上下文
	 * @param appType
	 *            app类型 值引用AppType中常量
	 * @param callback
	 *            绑定运营商回调
	 */
	public BindServiceDialog(Context context, int appType, BindCallBack callback) {
		super(context);
		this.appType = appType;
		this.mCallback = callback;
		dialog = this;
	}

	/**
	 * 绑定服务码回调
	 * 
	 * @author Administrator
	 *
	 */
	public interface BindCallBack {
		/**
		 * 绑定服务
		 * 
		 * @param resultCode
		 *            是否绑定状态
		 * @param resultMsg
		 *            绑定返回信息 成功返回改应用在运营商的基础服务地址 失败返回失败原因
		 */
		public void bindService(int resultCode, String resultMsg);
	}

	public interface BindClickListener {
		/**
		 * 点击绑定
		 * 
		 * @param dialog
		 * @param editText
		 *            输入栏
		 */
		public void clickBind(Dialog dialog, EditText editText);

		/**
		 * 点击取消
		 * 
		 * @param dialog
		 */
		public void cancelBind(Dialog dialog);
	}

	public void setBindClickListener(BindClickListener listener) {
		mListener = listener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_bindservice);
		initData();
		initView();
		initEvent();
	}

	@Override
	protected void initData() {
		super.initData();
	}

	private void initView() {
		initDialogSize();
		setCanceledOnTouchOutside(false);
		codeEditText = (EditText) findViewById(R.id.et_bindserviceDialog_code);
		bindButton = (Button) findViewById(R.id.bt_bindserviceDialog_bind);
		cancelButton = (Button) findViewById(R.id.bt_bindserviceDialog_cancel);
	}

	@Override
	protected void initEvent() {
		super.initEvent();
		codeEditText.setFocusable(true);
		codeEditText.setFocusableInTouchMode(true);
		codeEditText.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				Log.d(TAG, "hasFocus" + hasFocus);
				if (v.isFocused() && hasFocus) {
					dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
							| WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
					dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
				} else {
					dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
							| WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
					dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
				}
			}
		});
		bindButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 绑定
				if (null == mListener) {
					String code = codeEditText.getText().toString().trim();
					if (code.isEmpty()) {
						codeEditText.setHint(R.string.bindserviceDialog_noCode);
						codeEditText.setHintTextColor(Color.RED);
						return;
					}
					bindCode = code;
					showLoadingDialog(mContext, mContext.getResources().getString(R.string.bindserviceDialog_binding));
					Map<String, String> params = new HashMap<String, String>();
					params.put(WebConstants.BIND_CODE, code);
					WebVolleyUtils.getHttpResult(WebVolleyUtils.initRequestQueue(mContext),
							WebConstants.URL + WebConstants.URL_BIND, params, mHandler, WebConstants.WEBFLAG_BIND);

				} else {
					// 自定义
					mListener.clickBind(dialog, codeEditText);
				}
			}
		});
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 取消
				if (null == mListener) {
					dismiss();
				} else {
					mListener.cancelBind(dialog);
				}
			}
		});
	}

}
