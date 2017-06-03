package com.esint.provide.company.supervisory.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.esint.provide.company.supervisory.R;
import com.esint.provide.company.supervisory.activity.MainActivity;
import com.esint.provide.company.supervisory.bean.NotificationInfo;
import com.esint.provide.company.supervisory.bean.OrderInfo;
import com.esint.provide.company.supervisory.utils.JsonUtils;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;

/**
 * Created by MX on 2017/6/3.
 */

public class PushService extends GTIntentService {
    private static final String TAG = PushService.class.getSimpleName();
    @Override
    public void onReceiveServicePid(Context context, int i) {

    }

    @Override
    public void onReceiveClientId(Context context, String s) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + s);
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        String appid = msg.getAppid();
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();
        byte[] payload = msg.getPayload();
        String pkg = msg.getPkgName();
        String cid = msg.getClientId();

        // 第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
        boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
        Log.d(TAG, "call sendFeedbackMessage = " + (result ? "success" : "failed"));

        Log.d(TAG, "onReceiveMessageData -> " + "appid = " + appid + "\ntaskid = " + taskid + "\nmessageid = " + messageid + "\npkg = " + pkg
                + "\ncid = " + cid);

        if (payload == null) {
            Log.e(TAG, "receiver payload = null");
        } else {
            String data = new String(payload);
            Log.d(TAG, "receiver payload = " + data);
            try {
                NotificationInfo info = JsonUtils.getInstance().getNotification(data);
                createNotification(info);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean b) {

    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage gtCmdMessage) {

    }

    private void createNotification(NotificationInfo info){
        String type = info.getE_type();
        int typeImg = 0;
        switch (type){
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

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,  new Intent(this, MainActivity.class), 0);

        Notification notify= new Notification.Builder(this)
                .setSmallIcon(typeImg) // 设置状态栏中的小图片，尺寸一般建议在24×24， 这里也可以设置大图标
                .setTicker(info.getE_type_name())// 设置显示的提示文字
                        .setContentTitle(info.getE_type_name())// 设置显示的标题
                        .setContentText(info.getE_info())// 消息的详细内容
                        .setContentIntent(pendingIntent) // 关联PendingIntent
                        .setNumber(1) // 在TextView的右方显示的数字，可以在外部定义一个变量，点击累加setNumber(count),这时显示的和
                        .getNotification(); // 需要注意build()是在API level16及之后增加的，在API11中可以使用getNotificatin()来代替
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager manager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notify);
    }
}
