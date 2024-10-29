package com.example.irenginisismanusis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private EditText etInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etInputText = findViewById(R.id.etInputText);
        Button btnSubmitText = findViewById(R.id.btnSubmitText);

        btnSubmitText.setOnClickListener(v -> {
            String enteredText = etInputText.getText().toString();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("enteredText", enteredText);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
