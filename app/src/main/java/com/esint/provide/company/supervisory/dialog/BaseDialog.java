package com.esint.provide.company.supervisory.dialog;

import com.esint.provide.company.supervisory.R;
import com.github.ybq.android.spinkit.SpinKitView;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BaseDialog extends AlertDialog {
	protected Context mContext;

	protected BaseDialog(Context context) {
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
	 * 弹出提示
	 * 
	 * @param context
	 * @param msg
	 *            提示内容
	 */
	public void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
	



	protected void showLoading(View loadingView, String message){
		ImageView resultImageView = (ImageView)loadingView.findViewById(R.id.iv_loadingView_icon);
		SpinKitView loadingIcon = (SpinKitView)loadingView.findViewById(R.id.skv_loadingView_loading);
		TextView loadingTextView = (TextView)loadingView.findViewById(R.id.tv_loadingView_message);

		resultImageView.setVisibility(View.GONE);
		loadingIcon.setVisibility(View.VISIBLE);
		loadingTextView.setVisibility(View.VISIBLE);

		loadingTextView.setText(message);

	}

	protected void showResult(View loadingView, String message){
		ImageView resultImageView = (ImageView)loadingView.findViewById(R.id.iv_loadingView_icon);
		SpinKitView loadingIcon = (SpinKitView)loadingView.findViewById(R.id.skv_loadingView_loading);
		TextView loadingTextView = (TextView)loadingView.findViewById(R.id.tv_loadingView_message);

		resultImageView.setVisibility(View.VISIBLE);
		loadingIcon.setVisibility(View.GONE);
		loadingTextView.setVisibility(View.VISIBLE);

		loadingTextView.setText(message);
	}

}
