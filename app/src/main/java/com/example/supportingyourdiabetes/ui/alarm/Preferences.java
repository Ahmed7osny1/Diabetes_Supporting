package com.example.supportingyourdiabetes.ui.alarm;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.example.supportingyourdiabetes.R;


public class Preferences extends PreferenceActivity
{
  @Override
  protected void onCreate(Bundle bundle)
  {
    super.onCreate(bundle);
    addPreferencesFromResource(R.xml.preferences);
  }
}

