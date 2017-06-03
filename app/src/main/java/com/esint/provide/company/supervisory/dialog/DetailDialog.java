package com.esint.provide.company.supervisory.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.esint.provide.company.supervisory.R;
import com.esint.provide.company.supervisory.activity.BaseActivity;
import com.esint.provide.company.supervisory.bean.JsonResult;
import com.esint.provide.company.supervisory.bean.MessageInfo;
import com.esint.provide.company.supervisory.bean.OrderInfo;
import com.esint.provide.company.supervisory.utils.JsonUtils;
import com.esint.provide.company.supervisory.utils.OKHttpUtils;
import com.esint.provide.company.supervisory.utils.WebConstances;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MX on 2017/6/3.
 */

public class DetailDialog extends BaseDialog implements View.OnClickListener{
    private static final String TAG = DetailDialog.class.getSimpleName();
    private int orderType = 0;
    private Context mContext;
    private Button closeButton;
    private TextView typeTextView, timeTextView, messageTextView;
    private ImageView typeImageView;

    private MessageInfo mInfo;
    private RelativeLayout contentView;
    private View loadingView;

    private Handler mHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case OKHttpUtils.WEBFLAG_ERROR:
                    //网络请求错误
//                    dismiss();
                    break;
                case WebConstances.WEBFLAG_UPDATEMESSAGE:
                    //获得详情
                    try{
                        JsonResult resultJson = JsonUtils.getInstance().getResult(msg.obj.toString());
                        if(null != resultJson){
                            if(resultJson.getMark().equals(WebConstances.RESULT_MARK_OK)){
                                loadingView.setVisibility(View.GONE);
                                contentView.setVisibility(View.VISIBLE);
                                setValue(mInfo);

                                break;
                            }
                        }
                    }catch(Exception e){

                    }
                    loadingView.setVisibility(View.GONE);
                    contentView.setVisibility(View.VISIBLE);
                    setValue(mInfo);
//                    dismiss();
//                    BaseActivity.showToast(mContext, mContext.getResources().getString(R.string.toast_date_error));

                    break;
            }
        }
    };

    public DetailDialog(Context context, MessageInfo info, int type){
        super(context);
        mContext = context;
        mInfo = info;
        orderType = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_detail);
        initData();
        initView();
        initEvent();
        if(orderType == 0){
            //未读信息
            contentView.setVisibility(View.GONE);
            loadingView.setVisibility(View.VISIBLE);
            showLoading(loadingView, mContext.getResources().getString(R.string.loading_message_loading));
            updateMessage();
        }else{
            loadingView.setVisibility(View.GONE);
            contentView.setVisibility(View.VISIBLE);
            setValue(mInfo);
        }


    }
    protected void initData() {
    }

    private void initView(){
        initDialogSize();
        closeButton = (Button)findViewById(R.id.bt_detailActivity_close);
        typeTextView = (TextView)findViewById(R.id.tv_detailActivity_orderType);
        timeTextView = (TextView)findViewById(R.id.tv_detailActivity_orderTime);
        messageTextView = (TextView)findViewById(R.id.tv_detailActivity_orderMessage);
        typeImageView = (ImageView)findViewById(R.id.iv_detailActivity_type);
        contentView = (RelativeLayout)findViewById(R.id.rl_detailActivity_content);
        loadingView = findViewById(R.id.ic_detailActivity_loading);
    }

    protected void initEvent() {
        closeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_detailActivity_close:
                dismiss();
                break;
        }
    }

    private void setValue(MessageInfo info){
        int typeImg = 0;
        String typeName =  info.getE_type_name();
        switch (info.getE_type()){
            case OrderInfo.TYPE_SHOP:
                //商铺
                typeImg = R.drawable.ic_type_shop;
                break;
            case OrderInfo.TYPE_SOCIAL:
                //社工
                typeImg = R.drawable.ic_type_social;
                break;
            case OrderInfo.TYPE_CALL:
                //呼叫
                typeImg = R.drawable.ic_type_call;
                break;
            default:
                typeImg = R.drawable.ic_type_default;
                break;
        }
        Picasso.with(mContext).load(typeImg).placeholder(R.drawable.ic_type_default).error(R.drawable.ic_error).into(typeImageView);
        typeTextView.setText(info.getE_type_name());
        timeTextView.setText(info.getE_add_time());
        messageTextView.setText(info.getE_info());
    }

    private void updateMessage(){
        Map<String, String> params = new HashMap<String, String>();
        params.put(WebConstances.PARAMS_UPDATEMESSAGE_ID, mInfo.getE_id());
        params.put(WebConstances.PARAMS_UPDATEMESSAGE_READER, "");
        OKHttpUtils.getInstance().postRequest(WebConstances.BASE_URL + WebConstances.URL_MESSAGE_UPDATE, params, mHandler, WebConstances.WEBFLAG_UPDATEMESSAGE);
    }


}
