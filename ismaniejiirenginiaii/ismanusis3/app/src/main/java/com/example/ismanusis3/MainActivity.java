package com.example.ismanusis3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private AutoCompleteTextView autoCompleteTextView;
    private RatingBar ratingBar;
    private TimePicker timePicker;
    private DatePicker datePicker;
    private Spinner spinnerCity;
    private Switch switchConfirm;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Susiejame XML komponentus
        editTextName = findViewById(R.id.editTextName);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        ratingBar = findViewById(R.id.ratingBar);
        timePicker = findViewById(R.id.timePicker);
        datePicker = findViewById(R.id.datePicker);
        spinnerCity = findViewById(R.id.spinnerCity);
        switchConfirm = findViewById(R.id.switchConfirm);
        submitButton = findViewById(R.id.submitButton);

        // 2. Užpildome AutoCompleteTextView duomenimis iš strings.xml
        String[] departments = getResources().getStringArray(R.array.departments);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, departments);
        autoCompleteTextView.setAdapter(adapter);

        // 3. Spinner pasirinkimo pavyzdys
        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this, R.array.cities, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(cityAdapter);

        // 4. Mygtuko paspaudimo įvykis
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Skaityti įvestus duomenis
                String name = editTextName.getText().toString();
                String department = autoCompleteTextView.getText().toString();
                float rating = ratingBar.getRating();
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String city = spinnerCity.getSelectedItem().toString();
                boolean confirmed = switchConfirm.isChecked();

                // Sukuriame pranešimo tekstą
                String message = "Pavadinimas: " + name + "; Vertinimas: " + rating + "; Laikas: " + hour + ":" + minute +
                        "; Data: " + day + "/" + month + "/" + year + "; Padalinys: " + department + "; Miestas: " + city;

                // Rodome Snackbar
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
                snackbar.setAction("Patvirtinu", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Įrašyti duomenis į failą
                        saveToFile(name, message);
                    }
                });
                snackbar.show();
            }
        });
    }

    private void saveToFile(String name, String content) {
        try {
            FileOutputStream fos = openFileOutput(name.replaceAll("\\s", "") + ".txt", MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this, "Duomenys įrašyti sėkmingai", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
