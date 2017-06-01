package com.esint.communitytools.dialog;

import com.esint.communitytools.utils.CryptLib;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class ToolsBaseDialog extends AlertDialog {
	protected Context mContext;
	protected SpotsDialog mLoadingDialog;

	protected ToolsBaseDialog(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected void initData() {

	}

	protected void initEvent() {

	}

	/**
	 * 规定dialog大小
	 */
	protected void initDialogSize() {
		Window dialogWindow = getWindow();
		Display display = this.getWindow().getWindowManager().getDefaultDisplay();
		WindowManager.LayoutParams params = dialogWindow.getAttributes();
		params.height = (int) (display.getHeight() * 0.5);
		params.width = (int) (display.getWidth() * 0.4);
		dialogWindow.setAttributes(params);
	}

	/**
	 * 默认加载样式
	 * 
	 * @param context
	 * @param content
	 *            加载文字
	 */
	public void showLoadingDialog(Context context, String content) {
		mLoadingDialog = new SpotsDialog(context, content);
		mLoadingDialog.show();
	}

	/**
	 * 弹出提示
	 * 
	 * @param context
	 * @param msg
	 *            提示内容
	 */
	public void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	/**
	 * 加密
	 * 
	 * @param key
	 *            秘钥
	 * @param src
	 *            原文
	 * @return
	 */
	public String enCode(String key, String src) throws Exception {
		CryptLib _crypt = new CryptLib();
		String shaKey = CryptLib.SHA256(key, 32); // 32 bytes =
		String iv = CryptLib.SHA256(key, 16); // 16 bytes = 128 bit
		return _crypt.encrypt(src, shaKey, iv); // encrypt
	}

	/**
	 * 解密
	 * 
	 * @param key
	 *            秘钥
	 * @param src
	 *            密文
	 * @return
	 * @throws Exception
	 */
	public String deCode(String key, String src) throws Exception {
		CryptLib _crypt = new CryptLib();
		String shaKey = CryptLib.SHA256(key, 32); // 32 bytes =
		String iv = CryptLib.SHA256(key, 16); // 16 bytes = 128 bit
		return _crypt.decrypt(src, shaKey, iv); // decrypt
	}

	public void showKeyBroad(Context context, EditText editText) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
	}

	public void hideKeBroad(Context context, EditText editText) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		// imm.hideSoftInputFromInputMethod(editText.getWindowToken(), 0);
		imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
	}

}
