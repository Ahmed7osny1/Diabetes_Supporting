package com.example.supportingyourdiabetes.ui.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.supportingyourdiabetes.data.Alarm;

public class AlarmReceiver extends BroadcastReceiver
{
  private final String TAG = "AlarmMe";

  @Override
  public void onReceive(Context context, Intent intent)
  {
    Intent newIntent = new Intent(context, AlarmNotification.class);
    Alarm alarm = new Alarm(context);

    alarm.fromIntent(intent);
    alarm.toIntent(newIntent);
    newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);

    Log.i(TAG, "AlarmReceiver.onReceive('" + alarm.getTitle() + "')");

    context.startActivity(newIntent);
  }
}

