package com.example.ismanusis6;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView textView1, textView2;
    String selectedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        // Register context menu for TextViews
        registerForContextMenu(textView1);
        registerForContextMenu(textView2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_exit) {
            finish();  // Exit the app
            return true;
        } else if (id == R.id.submenu_time_input) {
            showTimePickerDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }


    }

    private void showTimePickerDialog() {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calculateTimeDifference(hourOfDay, minute);
            }
        }, hour, minute, true);

        timePickerDialog.show();
    }

    private void calculateTimeDifference(int inputHour, int inputMinute) {
        Calendar currentTime = Calendar.getInstance();
        int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);
        int currentMinute = currentTime.get(Calendar.MINUTE);

        int differenceInMinutes = ((currentHour - inputHour) * 60) + (currentMinute - inputMinute);

        // Show the time difference in AlertDialog
        new AlertDialog.Builder(this)
                .setTitle("Time Difference")
                .setMessage("Difference: " + Math.abs(differenceInMinutes) + " minutes")
                .setPositiveButton("OK", null)
                .show();

        // Also update the TextView
        textView1.setText("Time difference: " + Math.abs(differenceInMinutes) + " minutes");
    }

    // Context Menu creation for TextViews
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.textView1 || v.getId() == R.id.textView2) {
            selectedText = ((TextView) v).getText().toString();
            menu.setHeaderTitle("Select Action");
            menu.add(0, v.getId(), 0, "Show character count");
            menu.add(0, v.getId(), 1, "Display characters one by one");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals("Show character count")) {
            showCharacterCountDialog();
        } else if (item.getTitle().equals("Display characters one by one")) {
            displayCharactersOneByOne();
        }
        return true;
    }

    private void showCharacterCountDialog() {
        int charCount = selectedText.length();
        new AlertDialog.Builder(this)
                .setTitle("Character Count")
                .setMessage("Character count: " + charCount)
                .setPositiveButton("OK", null)
                .show();

        textView2.setText("Character count: " + charCount);
    }

    private void displayCharactersOneByOne() {
        final int[] index = {0};
        final StringBuilder output = new StringBuilder();
        final android.os.Handler handler = new android.os.Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (index[0] < selectedText.length()) {
                    output.append(selectedText.charAt(index[0]));
                    textView2.setText(output.toString());
                    index[0]++;
                    handler.postDelayed(this, 1000);  // 1 second delay
                }
            }
        };
        handler.post(runnable);
    }
}
