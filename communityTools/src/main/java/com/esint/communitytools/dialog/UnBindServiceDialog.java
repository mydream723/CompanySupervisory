package com.esint.communitytools.dialog;

import java.util.ArrayList;
import java.util.List;

import com.esint.communitytools.R;
import com.esint.communitytools.bean.CService;
import com.esint.communitytools.data.PropertyKey;
import com.esint.communitytools.data.ResultCode;
import com.esint.communitytools.utils.CommunityHelper;
import com.squareup.picasso.Picasso;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 绑定运营商
 * 
 * @author Administrator
 *
 */
public class UnBindServiceDialog extends ToolsBaseDialog {
	private Dialog dialog;
	private CService mService;
	private UnBindClickListener mListener;
	private UnBindCallBack mCallBack;
	private TextView titleTextView;
	private ImageView bindImageView;
	private Button unBindButton, cancelButton;

	public UnBindServiceDialog(Context context, UnBindCallBack callback) {
		super(context);
		mContext = context;
		mCallBack = callback;
		dialog = this;
	}

	/**
	 * 解绑回调
	 * 
	 * @author Administrator
	 *
	 */
	public interface UnBindCallBack {
		/**
		 * 
		 * @param dialog
		 *            解绑dialog
		 */
		public void unbind(int resultCode);
	}

	public interface UnBindClickListener {
		/**
		 * 点击绑定
		 * 
		 * @param dialog
		 * @param editText
		 *            输入栏
		 */
		public void clickUnBind(Dialog dialog);

		/**
		 * 点击取消
		 * 
		 * @param dialog
		 */
		public void cancelUnBind(Dialog dialog);
	}

	public void setBindClickListener(UnBindClickListener listener) {
		mListener = listener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_unbindservice);
		initData();
		initView();
		initEvent();
	}

	@Override
	protected void initData() {
		super.initData();
		mService = CommunityHelper.getInstance(mContext).getServiceInfo();
	}

	private void initView() {
		initDialogSize();
		setCanceledOnTouchOutside(false);
		titleTextView = (TextView) findViewById(R.id.tv_unbindserviceDialog_title);
		titleTextView.setText(mService.getServiceName());
		bindImageView = (ImageView) findViewById(R.id.iv_unbindserviceDialog_img);
		unBindButton = (Button) findViewById(R.id.bt_unbindserviceDialog_unbind);
		cancelButton = (Button) findViewById(R.id.bt_unbindserviceDialog_cancel);
		Picasso.with(mContext).load(R.drawable.ic_launcher).placeholder(R.drawable.ic_launcher)
				.error(R.drawable.ic_launcher).into(bindImageView);

	}

	@Override
	protected void initEvent() {
		super.initEvent();
		unBindButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 解绑
				if (null == mListener) {
					if (null != mCallBack) {
						List<String> keys = new ArrayList<String>();
						keys.add(PropertyKey.PROPERTY_SERVICECODE);
						keys.add(PropertyKey.PROPERTY_SERVICEICON);
						keys.add(PropertyKey.PROPERTY_SERVICENAME);
						boolean isUnbinded = CommunityHelper.getInstance(mContext).unBindService();
						if (isUnbinded)
							mCallBack.unbind(ResultCode.RESULT_SUCCESS);
						else
							mCallBack.unbind(ResultCode.RESULT_FAIL);
					}
					dismiss();
				} else {
					// 自定义
					mListener.clickUnBind(dialog);
				}
			}
		});
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 取消
				if (null == mListener) {
					dismiss();
				} else {
					mListener.cancelUnBind(dialog);
				}
			}
		});
	}

}
