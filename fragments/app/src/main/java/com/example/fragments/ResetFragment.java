package com.example.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ResetFragment extends Fragment {

    private OnResetListener listener;

    public interface OnResetListener {
        void onReset();
    }

    public ResetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnResetListener) {
            listener = (OnResetListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnResetListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment_reset layout
        View view = inflater.inflate(R.layout.fragment_reset, container, false);
        Button btnReset = view.findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onReset();
                }
            }
        });
        return view;
    }
}
