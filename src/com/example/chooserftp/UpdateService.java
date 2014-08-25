package com.example.chooserftp;



import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import java.util.ArrayList;
import android.telephony.SmsManager;
import android.net.Uri;
import android.content.ContentValues;
import android.content.Intent;

public class UpdateService extends Service {

    BroadcastReceiver mReceiver;

@Override
public void onCreate() {
    super.onCreate();
    // register receiver that handles screen on and screen off logic
    IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
    filter.addAction(Intent.ACTION_SCREEN_OFF);
    mReceiver = new MyReceiver();
    registerReceiver(mReceiver, filter);
}

@Override
public void onDestroy() {

    unregisterReceiver(mReceiver);
    Log.i("onDestroy Reciever", "Called");

    super.onDestroy();
}

@Override
public void onStart(Intent intent, int startId) {
    boolean screenOn = intent.getBooleanExtra("screen_state", false);
    if (!screenOn) {
        Log.i("screenON", "Called");
        Toast.makeText(getApplicationContext(), "Awake", Toast.LENGTH_LONG)
                .show();
        sendSMS();
     
    } else {
        Log.i("screenOFF", "Called");
        // Toast.makeText(getApplicationContext(), "Sleep",
        // Toast.LENGTH_LONG)
        // .show();
    }
    
}

@Override
public IBinder onBind(Intent intent) {
    // TODO Auto-generated method stub
    return null;
}

public void sendSMS() {
    //String phoneNo = "0123456789";
    //String message = "Hello World!";

    SmsManager smsManager = SmsManager.getDefault();
    smsManager.sendTextMessage("9096414646", null, "In danger need help", null, null);
    
    Toast.makeText(getApplicationContext(), "Message Sent!", Toast.LENGTH_LONG).show();
}


public void sendLongSMS() {    	 
  
    SmsManager smsManager = SmsManager.getDefault();
    ArrayList<String> parts = smsManager.divideMessage("I am in danger help me"); 
    smsManager.sendMultipartTextMessage("9096414646", null, parts, null, null);
    
    Toast.makeText(getApplicationContext(), "Message Sent!", Toast.LENGTH_LONG).show();
}


public void invokeSMSApp() {
    Intent smsIntent = new Intent(Intent.ACTION_VIEW);

    smsIntent.putExtra("sms_body","I am in Danger"); //"Hello World!"); 
    smsIntent.putExtra("address", "9096414646"); //"0123456789");
    smsIntent.setType("vnd.android-dir/mms-sms");

    startActivity(smsIntent);
}



}