package com.example.haresh.aashasmsserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsHandler extends BroadcastReceiver {

    final static String TAG = "SMS_Handler";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Message recieved");
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdus = (Object[]) bundle.get("pdus");
                assert pdus != null;
                for (Object aPdusObj : pdus) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj);

                    String senderNum = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i(TAG, "senderNum: " + senderNum + "; message: " + message);
                }
            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e.getMessage());
        }
    }

    public static void sendSms(String msg, String phoneNo) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Log.d(TAG, "Sms Sent");
        } catch (Exception e) {
            Log.d(TAG, "Sms Not Sent");
        }
    }
}