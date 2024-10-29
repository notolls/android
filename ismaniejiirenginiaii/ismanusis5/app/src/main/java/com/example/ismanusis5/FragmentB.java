package com.example.ismanusis5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentB#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentB extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);

        TextView textStats = view.findViewById(R.id.textStats);

        // Retrieve the selected item and calculate statistics
        String selectedItem = getArguments().getString("selectedItem", "");
        int length = selectedItem.length();
        int vowels = (int) selectedItem.toLowerCase().chars().filter(ch -> "aeiou".indexOf(ch) != -1).count();
        int upperCase = (int) selectedItem.chars().filter(Character::isUpperCase).count();
        int lowerCase = (int) selectedItem.chars().filter(Character::isLowerCase).count();

        textStats.setText("Length: " + length + "\nVowels: " + vowels + "\nUppercase: " + upperCase + "\nLowercase: " + lowerCase);

        return view;
    }
}
