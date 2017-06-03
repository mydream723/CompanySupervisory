package com.esint.provide.company.supervisory.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esint.provide.company.supervisory.R;
import com.github.ybq.android.spinkit.SpinKitView;

/**
 * Created by MX on 2017/6/1.
 */

public class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected SharedPreferences mSharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    protected void initData(){
        mSharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
    }

    protected void initEvent(){

    }

    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
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
