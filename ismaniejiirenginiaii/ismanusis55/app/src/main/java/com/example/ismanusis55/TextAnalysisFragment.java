package com.example.ismanusis55;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class TextAnalysisFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text_analysis, container, false);
        TextView textView = view.findViewById(R.id.textView);

        String selectedItem = getArguments().getString("selectedItem", "");
        int length = selectedItem.length();
        int vowels = countVowels(selectedItem);
        int upperCase = countUpperCase(selectedItem);
        int lowerCase = countLowerCase(selectedItem);

        textView.setText("ilgis: " + length + "\nVowels: " + vowels + "\ndidziosios: " + upperCase + "\nmazosios: " + lowerCase);
        return view;
    }

    private int countVowels(String text) {
        int count = 0;
        for (char c : text.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    private int countUpperCase(String text) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                count++;
            }
        }
        return count;
    }

    private int countLowerCase(String text) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (Character.isLowerCase(c)) {
                count++;
            }
        }
        return count;
    }
}
