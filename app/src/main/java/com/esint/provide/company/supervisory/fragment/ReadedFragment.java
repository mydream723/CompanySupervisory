package com.esint.provide.company.supervisory.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esint.provide.company.supervisory.R;

/**
 * Created by MX on 2017/6/1.
 */

public class ReadedFragment extends BaseFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_readed, null);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view){

    }

    @Override
    protected void initData() {
        super.initData();
        mContext = getActivity();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }
}
