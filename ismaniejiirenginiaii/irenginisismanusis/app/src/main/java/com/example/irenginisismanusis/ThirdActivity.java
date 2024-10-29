package com.example.irenginisismanusis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    private TextView tvOriginalText;
    private TextView tvWordCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tvOriginalText = findViewById(R.id.tvOriginalText);
        tvWordCount = findViewById(R.id.tvWordCount);
        Button btnCountWords = findViewById(R.id.btnCountWords);

        String text = getIntent().getStringExtra("text");
        tvOriginalText.setText(text);

        btnCountWords.setOnClickListener(v -> {
            int wordCount = countWords(text);
            String result = "Sakinyje \"" + text + "\" esti " + wordCount + " žodžiai";
            tvWordCount.setText(result);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("modifiedText", result);
            setResult(RESULT_OK, resultIntent);
        });
    }

    private int countWords(String sentence) {
        return sentence.trim().split("\\s+").length;
    }
}
