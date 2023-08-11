package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNum1, editTextNum2, editTextResult;
    private String currentNumber = "";
    private double num1 = 0;
    private double num2 = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNum1 = findViewById(R.id.editTextNum1);
        editTextNum2 = findViewById(R.id.editTextNum2);
        editTextResult = findViewById(R.id.editTextResult);

        setupDigitButton(R.id.btn0, "0");
        setupDigitButton(R.id.btn1, "1");
        setupDigitButton(R.id.btn2, "2");
        setupDigitButton(R.id.btn3, "3");
        setupDigitButton(R.id.btn4, "4");
        setupDigitButton(R.id.btn5, "5");
        setupDigitButton(R.id.btn6, "6");
        setupDigitButton(R.id.btn7, "7");
        setupDigitButton(R.id.btn8, "8");
        setupDigitButton(R.id.btn9, "9");

        setupOperatorButton(R.id.btnAdd, "+");
        setupOperatorButton(R.id.btnSubtract, "-");
        setupOperatorButton(R.id.btnMultiply, "*");
        setupOperatorButton(R.id.btnDivide, "/");

        Button btnEquals = findViewById(R.id.btnEquals);
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performOperation();
            }
        });

        setupDecimalButton(R.id.btnDecimal, ".");

        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetCalculator();
            }
        });
    }

    private void setupDigitButton(int buttonId, final String digit) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendNumber(digit);
            }
        });
    }

    private void setupOperatorButton(int buttonId, final String op) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentNumber.isEmpty()) {
                    if (num1 == 0) {
                        num1 = Double.parseDouble(currentNumber);
                        editTextNum1.setText(currentNumber);
                    } else if (!operator.isEmpty() && num2 == 0) {
                        num2 = Double.parseDouble(currentNumber);
                        editTextNum2.setText(currentNumber);
                        performOperation();
                        editTextNum1.setText(editTextResult.getText());
                        num2 = 0;
                    }
                    currentNumber = "";
                    operator = op;
                }
            }
        });
    }

    private void setupDecimalButton(int buttonId, final String decimal) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentNumber.contains(decimal)) {
                    appendNumber(decimal);
                }
            }
        });
    }

    private void appendNumber(String num) {
        currentNumber += num;
        if (operator.isEmpty()) {
            editTextNum1.setText(currentNumber);
        } else {
            editTextNum2.setText(currentNumber);
        }
    }

    private void performOperation() {
        if (!operator.isEmpty() && !currentNumber.isEmpty()) {
            num2 = Double.parseDouble(currentNumber);
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        editTextResult.setText("Error");
                        return;
                    }
                    break;
            }

            editTextResult.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
            operator = "";
            num1 = result;
            num2 = 0;

        }
    }

    private void resetCalculator() {
        editTextNum1.setText("");
        editTextNum2.setText("");
        editTextResult.setText("");
        currentNumber = "";
        num1 = 0;
        num2 = 0;
        operator = "";
    }
}
