package com.example.ismanusis7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver batteryLevelReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox batteryTrackingCheckbox = findViewById(R.id.batteryTrackingCheckbox);
        Button saveButton = findViewById(R.id.saveButton);

        SharedPreferences prefs = getSharedPreferences("BatteryPrefs", MODE_PRIVATE);
        batteryTrackingCheckbox.setChecked(prefs.getBoolean("trackingEnabled", false));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("trackingEnabled", batteryTrackingCheckbox.isChecked());
                editor.apply();
            }
        });

        // Registruoti BroadcastReceiver
        batteryLevelReceiver = new BatteryLevelReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryLevelReceiver);
    }
}
