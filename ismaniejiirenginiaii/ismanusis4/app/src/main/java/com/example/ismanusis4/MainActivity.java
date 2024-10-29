package com.example.ismanusis4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "WebViewPrefs";
    private static final String KEY_LAST_URL = "last_url";

    private EditText urlEditText;
    private Button loadButton;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlEditText = findViewById(R.id.urlEditText);
        loadButton = findViewById(R.id.loadButton);
        webView = findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());

        // Load saved URL if available
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String lastUrl = prefs.getString(KEY_LAST_URL, "https://www.");
        urlEditText.setText(lastUrl);
        webView.loadUrl(lastUrl);

        loadButton.setOnClickListener(v -> {
            String url = urlEditText.getText().toString();
            webView.loadUrl(url);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(KEY_LAST_URL, url);
            editor.apply();
            Toast.makeText(this, "Loading " + url, Toast.LENGTH_SHORT).show();
        });

        // Handle back button to navigate back in WebView history
        webView.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == android.view.KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                webView.goBack();
                return true;
            }
            return false;
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            Toast.makeText(this, "Press BACK again to close the app", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }
}
