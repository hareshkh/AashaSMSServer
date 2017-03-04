package com.example.haresh.aashasmsserevr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsHandler extends BroadcastReceiver {

    static String recievedMSG = null;
    static String TAG = "smsmanager";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage msg;

        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");

            assert smsObj != null;
            for (Object object : smsObj) {
                msg = SmsMessage.createFromPdu((byte[]) object);

                Date date = new Date(msg.getTimestampMillis());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String receiveTime = format.format(date);
                Log.d(TAG, "onReceive() called with: " + "context = [" + context + "], intent = [" + intent + "]");

                if (msg.getOriginatingAddress().equals("+919876543210")) {
                    // This recievedMSG is the message we get. If condition helps us get the message from a particular user only.
                    recievedMSG = msg.getDisplayMessageBody();
                }
            }
        }
    }

    public static void sendSms(String msg, String phoneNo, Context context) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(context, "Message Sent", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }
}