package com.esint.provide.company.supervisory;

import android.app.Application;
import android.os.Handler;
import android.os.Message;

import com.esint.provide.company.supervisory.utils.Constances;

/**
 * Created by MX on 2017/6/4.
 */

public class SupervisoryApplication extends Application {
    private static final String TAG = SupervisoryApplication.class.getSimpleName();
    private static RefreshHandler handler;
    @Override
    public void onCreate() {
        super.onCreate();
        if (handler == null) {
            handler = new RefreshHandler();
        }
    }

    public static class RefreshHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    public static void sendMessage(Message msg) {
        handler.sendMessage(msg);
        switch (msg.what){
            case Constances.FLAG_REFRESH:

                break;
        }
    }
}
