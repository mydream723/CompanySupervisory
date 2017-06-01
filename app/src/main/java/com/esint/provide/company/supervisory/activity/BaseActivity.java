package com.esint.provide.company.supervisory.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

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
}
