package com.example.ismanusis7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.BatteryManager;

public class BatteryLevelReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Gauti akumuliatoriaus lygį
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level / (float) scale * 100;

        // Patikrinkite, ar sekimas įjungtas
        SharedPreferences prefs = context.getSharedPreferences("BatteryPrefs", Context.MODE_PRIVATE);
        boolean trackingEnabled = prefs.getBoolean("trackingEnabled", false);

        if (trackingEnabled && batteryPct < 20) { // Pavyzdžiui, 20% akumuliatoriaus lygio
            // Pridėti logiką, kad rodytų pranešimą
            NotificationHelper.sendNotification(context, batteryPct);
        }
    }
}
