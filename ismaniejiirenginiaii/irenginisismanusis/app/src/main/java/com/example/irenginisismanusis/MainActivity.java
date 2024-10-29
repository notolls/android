package com.example.irenginisismanusis;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplayText;
    private String textFromSecondActivity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplayText = findViewById(R.id.tvDisplayText);
        Button btnEnterText = findViewById(R.id.btnEnterText);
        Button btnCountWords = findViewById(R.id.btnCountWords);
        Button btnSendText = findViewById(R.id.btnSendText);

        // Start SecondActivity
        btnEnterText.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            enterTextLauncher.launch(intent);
        });

        // Start ThirdActivity
        btnCountWords.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            intent.putExtra("text", textFromSecondActivity);
            countWordsLauncher.launch(intent);
        });

        // Implicit Intent to share the text
        btnSendText.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, textFromSecondActivity);
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Siųsti tekstą su rezultatais"));
        });
    }

    // Receive data from SecondActivity
    private final ActivityResultLauncher<Intent> enterTextLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    textFromSecondActivity = result.getData().getStringExtra("enteredText");
                    tvDisplayText.setText(textFromSecondActivity);
                }
            }
    );

    // Receive data from ThirdActivity
    private final ActivityResultLauncher<Intent> countWordsLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    String modifiedText = result.getData().getStringExtra("modifiedText");
                    tvDisplayText.setText(modifiedText);
                }
            }
    );
}
