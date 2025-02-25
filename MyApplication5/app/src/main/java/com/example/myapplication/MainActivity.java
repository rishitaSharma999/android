package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private String input = "";
    private String operator = "";
    private double num1 = 0;
    private double num2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Set click listeners for all buttons
        int[] buttonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
                R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
                R.id.btn_8, R.id.btn_9, R.id.btn_add, R.id.btn_subtract,
                R.id.btn_multiply, R.id.btn_divide, R.id.btn_equals, R.id.btn_clear
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(this::onButtonClick);
        }
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                input = "";
                operator = "";
                num1 = 0;
                num2 = 0;
                display.setText("0");
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (!input.isEmpty()) {
                    num1 = Double.parseDouble(input);
                    operator = buttonText;
                    input = "";
                }
                break;
            case "=":
                if (!input.isEmpty() && !operator.isEmpty()) {
                    num2 = Double.parseDouble(input);
                    double result = calculate(num1, num2, operator);
                    display.setText(String.valueOf(result));
                    input = String.valueOf(result);
                    operator = "";
                }
                break;
            default:
                input += buttonText;
                display.setText(input);
                break;
        }
    }

    private double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return 0; // Handle division by zero
                }
            default:
                return 0;
        }
    }
}