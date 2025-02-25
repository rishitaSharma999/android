package com.example.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DecrementFragment extends Fragment {

    private OnDecrementListener listener;

    public interface OnDecrementListener {
        void onDecrement();
    }

    public DecrementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDecrementListener) {
            listener = (OnDecrementListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDecrementListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment_decrement layout
        View view = inflater.inflate(R.layout.fragment_decrement, container, false);
        Button btnDecrement = view.findViewById(R.id.btnDecrement);
        btnDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDecrement();
                }
            }
        });
        return view;
    }
}
