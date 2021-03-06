package com.goalgetter.goalgetterroot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class FinancialActivity extends AppCompatActivity { //Logic for Financial Goal

    private EditText financialUserText;
    private EditText financialCurrentUserText;
    private Spinner financialSpinner;

    private String financialNotifArray[] = {"None","Daily","Weekly","Monthly"};// Array of choices
    private double financialGoal = 0;
    private double financialCurrentGoal = 0;
    public static Boolean financialEditMode = false;
    public static int financialPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_financial_goal);
        financialUserText = (EditText) findViewById(R.id.financialUserText);
        financialCurrentUserText = (EditText) findViewById(R.id.financialCurrentUserText);
        financialSpinner = (Spinner) findViewById(R.id.financialSpinner);// Selection of the spinner

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,financialNotifArray);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        financialSpinner.setAdapter(spinnerArrayAdapter);

        final ImageButton addGoalFinancialBtn = (ImageButton) findViewById(R.id.addGoalFinancialButton);

        if(financialEditMode) { //Sets text and spinner to the what is being edited.
            financialUserText.setText(MainActivity.financialList.get(financialPosition).getFinancialGoal());
            financialCurrentUserText.setText(MainActivity.financialList.get(financialPosition).getFinancialCurrentGoal());
            financialSpinner.setSelection(MainActivity.financialList.get(financialPosition).getFinancialNotifPos());
        }
        else addGoalFinancialBtn.setVisibility(View.INVISIBLE);// Set Invisible if user not editing

        financialUserText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (financialEditMode && financialUserText.getText().toString().length() > 0)
                    financialGoal = Double.parseDouble(financialUserText.getText().toString());
                else
                    financialGoal = 0;

                if(financialGoal > 0 && financialCurrentGoal >= 0 && financialGoal - financialCurrentGoal > 0)
                    addGoalFinancialBtn.setVisibility(View.VISIBLE);
                else addGoalFinancialBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(financialUserText.getText().toString().length() > 0)
                    financialGoal = Double.parseDouble(financialUserText.getText().toString());
                else
                    financialGoal = 0;

                if(financialGoal > 0 && financialCurrentGoal >= 0 && financialGoal - financialCurrentGoal > 0)
                    addGoalFinancialBtn.setVisibility(View.VISIBLE);
                else addGoalFinancialBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        financialCurrentUserText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (financialEditMode && financialCurrentUserText.getText().toString().length() > 0)
                    financialCurrentGoal = Double.parseDouble(financialCurrentUserText.getText().toString());
                else
                    financialCurrentGoal = 0;

                if(financialGoal > 0 && financialCurrentGoal > 0 && financialGoal - financialCurrentGoal > 0)
                    addGoalFinancialBtn.setVisibility(View.VISIBLE);
                else addGoalFinancialBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(financialCurrentUserText.getText().toString().length() > 0)
                    financialCurrentGoal = Double.parseDouble(financialCurrentUserText.getText().toString());
                else
                    financialCurrentGoal = 0;

                if(financialGoal > 0 && financialCurrentGoal > 0 && financialGoal - financialCurrentGoal > 0)
                    addGoalFinancialBtn.setVisibility(View.VISIBLE);
                else addGoalFinancialBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        addGoalFinancialBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        FinancialEvent financialEventObj = new FinancialEvent(financialUserText.getText().toString(),financialCurrentUserText.getText().toString(), financialSpinner.getSelectedItem().toString());
                        if(!financialEditMode) {
                            MainActivity.financialList.add(financialEventObj);

                            Toast.makeText(getApplicationContext(),
                                    "Financial Goal created", Toast.LENGTH_LONG).show();
                        }
                        else {
                            financialEditMode = false;
                            MainActivity.financialList.set(financialPosition, financialEventObj);
                            Toast.makeText(getApplicationContext(),
                                    "Financial Goal edited", Toast.LENGTH_LONG).show();
                        }
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }
}
