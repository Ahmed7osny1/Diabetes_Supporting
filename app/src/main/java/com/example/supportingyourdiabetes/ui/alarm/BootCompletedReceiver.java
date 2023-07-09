package com.example.supportingyourdiabetes.ui.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.supportingyourdiabetes.adapter.AlarmListAdapter;

public class BootCompletedReceiver extends BroadcastReceiver
{
  @Override
  public void onReceive(Context context, Intent intent)
  {
    // just create AlarmListAdapter and it will load alarms and start them
    new AlarmListAdapter(context);
  }
}

