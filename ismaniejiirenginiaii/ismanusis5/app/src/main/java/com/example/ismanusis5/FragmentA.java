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
 * Use the {@link FragmentA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentA extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        TextView letterCount = view.findViewById(R.id.letterCount);

        // Retrieve the selected item and count 'A's
        String selectedItem = getArguments().getString("selectedItem", "");
        int count = (int) selectedItem.toLowerCase().chars().filter(ch -> ch == 'a').count();
        letterCount.setText("Count of 'A': " + count);

        return view;
    }
}
