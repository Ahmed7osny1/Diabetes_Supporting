package com.example.supportingyourdiabetes.ui.alarm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.supportingyourdiabetes.R;
import com.example.supportingyourdiabetes.adapter.AlarmListAdapter;
import com.example.supportingyourdiabetes.data.Alarm;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;


public class AlarmFragment extends AppCompatActivity {


    private final String TAG = "AlarmMe";

    public ListView mAlarmList;
    private AlarmListAdapter mAlarmListAdapter;
    private Alarm mCurrentAlarm;

    public final int NEW_ALARM_ACTIVITY = 0;
    public final int EDIT_ALARM_ACTIVITY = 1;
    public final static  int PREFERENCES_ACTIVITY = 2;
    public final int ABOUT_ACTIVITY = 3;

    private final int CONTEXT_MENU_EDIT = 0;
    private final int CONTEXT_MENU_DELETE = 1;
    private final int CONTEXT_MENU_DUPLICATE = 2;
    private ActionMode mActionMode;
    ImageView imageView;

    FloatingActionButton floating;

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.fragment_alarm);

        Objects.requireNonNull(getSupportActionBar()).hide();

        Log.i(TAG, "AlarmMe.onCreate()");

        mAlarmList = (ListView)findViewById(R.id.alarm_list);
        floating = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Editt.class);

                mCurrentAlarm = new Alarm(getApplicationContext());
                mCurrentAlarm.toIntent(intent);

                AlarmFragment.this.startActivityForResult(intent, NEW_ALARM_ACTIVITY);
            }
        });

        mAlarmListAdapter = new AlarmListAdapter(this);
        mAlarmList.setAdapter(mAlarmListAdapter);
        mAlarmList.setOnItemClickListener(mListOnItemClickListener);
        registerForContextMenu(mAlarmList);

        mCurrentAlarm = null;

        mAlarmList.setOnLongClickListener(new View.OnLongClickListener() {
            // Called when the user long-clicks on someView
            public boolean onLongClick(View view) {
                if (mActionMode != null) {
                    return false;
                }

                // Start the CAB (Context Action Bar) using the ActionMode.Callback defined above
                mActionMode = AlarmFragment.this.startActionMode(mActionModeCallback);
              //  view.setSelected(true);
                return true;
            }
        });
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i(TAG, "AlarmMe.onDestroy()");
//    mAlarmListAdapter.save();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.i(TAG, "AlarmMe.onResume()");
        mAlarmListAdapter.updateAlarms();
    }

    public void onAddAlarmClick(View view)
    {
        Intent intent = new Intent(getBaseContext(), Editt.class);

        mCurrentAlarm = new Alarm(this);
        mCurrentAlarm.toIntent(intent);

        AlarmFragment.this.startActivityForResult(intent, NEW_ALARM_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ALARM_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                mCurrentAlarm.fromIntent(data);
                mAlarmListAdapter.add(mCurrentAlarm);
            }
            mCurrentAlarm = null;
        } else if (requestCode == EDIT_ALARM_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                mCurrentAlarm.fromIntent(data);
                mAlarmListAdapter.update(mCurrentAlarm);
            }
            mCurrentAlarm = null;
        } else if (requestCode == PREFERENCES_ACTIVITY) {
            mAlarmListAdapter.onSettingsUpdated();

        }

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.alarm_list) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

            menu.setHeaderTitle(mAlarmListAdapter.getItem(info.position).getTitle());
            menu.add(Menu.NONE, CONTEXT_MENU_EDIT, Menu.NONE, "Modify");
            menu.add(Menu.NONE, CONTEXT_MENU_DELETE, Menu.NONE, "Remove");

        }
    }

     @Override
     public boolean onContextItemSelected(MenuItem item)
     {
     AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
     int index = item.getItemId();

     if (index == CONTEXT_MENU_EDIT)
     {
     Intent intent = new Intent(getBaseContext(), Editt.class);

     mCurrentAlarm = mAlarmListAdapter.getItem(info.position);
     mCurrentAlarm.toIntent(intent);
     startActivityForResult(intent, EDIT_ALARM_ACTIVITY);
     }
     else if (index == CONTEXT_MENU_DELETE)
     {
     mAlarmListAdapter.delete(info.position);
     }


     return true;
     }




    public void onClick(View v) {
        if (v.getId() == R.id.checkBox_alarm_active) {
            Switch checkBox = (Switch) v;

            if (checkBox.isChecked()) {
                Toast.makeText(this, "HELLO", Toast.LENGTH_LONG).show();
            }
        }

    }

    private AdapterView.OnItemClickListener mListOnItemClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            Intent intent = new Intent(getBaseContext(), Editt.class);
            mCurrentAlarm = mAlarmListAdapter.getItem(position);
            mCurrentAlarm.toIntent(intent);
            AlarmFragment.this.startActivityForResult(intent, EDIT_ALARM_ACTIVITY);
        }
    };
    private  ActionMode.Callback mActionModeCallback =new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.setTitle("Selected");
            MenuInflater inflater = actionMode.getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mActionMode= null;
        }
    };

}