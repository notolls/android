package com.example.ismanusis55;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class CountAFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count_a, container, false);
        TextView textView = view.findViewById(R.id.textView);

        String selectedItem = getArguments().getString("selectedItem", "");
        int countA = countOccurrences(selectedItem, 'a') + countOccurrences(selectedItem, 'A');

        textView.setText("A's kiekis: " + countA);
        return view;
    }

    private int countOccurrences(String text, char character) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (c == character) {
                count++;
            }
        }
        return count;
    }
}
