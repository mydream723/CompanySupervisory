package com.esint.provide.company.supervisory.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esint.provide.company.supervisory.R;
import com.github.ybq.android.spinkit.SpinKitView;

/**
 * Created by MX on 2017/6/1.
 */

public class BaseFragment extends Fragment {
    protected Context mContext;
    protected SharedPreferences mSharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void initData(){
        mContext = getActivity();
        mSharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
    }

    protected void initEvent(){

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
