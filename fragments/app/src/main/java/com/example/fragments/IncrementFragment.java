package com.example.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class IncrementFragment extends Fragment {

    private OnIncrementListener listener;

    public interface OnIncrementListener {
        void onIncrement();
    }

    public IncrementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnIncrementListener) {
            listener = (OnIncrementListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnIncrementListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment_increment layout
        View view = inflater.inflate(R.layout.fragment_increment, container, false);
        Button btnIncrement = view.findViewById(R.id.btnIncrement);
        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onIncrement();
                }
            }
        });
        return view;
    }
}