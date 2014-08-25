package com.example.chooserftp;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
private boolean screenOff;

@Override
public void onReceive(Context context, Intent intent) {
    if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
        screenOff = true;
    } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
        screenOff = false;
    }
    Intent i = new Intent(context, UpdateService.class);
    i.putExtra("screen_state", screenOff);
    context.startService(i);
}

}