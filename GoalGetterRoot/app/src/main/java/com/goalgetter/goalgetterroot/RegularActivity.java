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

public class RegularActivity extends AppCompatActivity { //Logic for Regular Goal

    private EditText regularUserText;
    private Spinner regularSpinner;

    public static Boolean regularEditMode = false;
    private String regularNotifArray[] = {"None","Daily","Weekly","Monthly"};// Array of choices
    public static int regularPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_regular_goal);
        regularUserText = (EditText) findViewById(R.id.regularUserText);
        regularSpinner = (Spinner) findViewById(R.id.regularSpinner);// Selection of the spinner

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,regularNotifArray);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        regularSpinner.setAdapter(spinnerArrayAdapter);

        final ImageButton addGoalRegularBtn = (ImageButton) findViewById(R.id.addGoalRegularButton);

        if(regularEditMode) { //Sets text and spinner to the what is being edited.
            regularUserText.setText(MainActivity.regularList.get(regularPosition).getRegularGoal());
            regularSpinner.setSelection(MainActivity.regularList.get(regularPosition).getRegularNotifPos());
        }
        else addGoalRegularBtn.setVisibility(View.INVISIBLE);// Set Invisible if user not editing

        regularUserText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(regularUserText.getText().toString().length() > 0)
                    addGoalRegularBtn.setVisibility(View.VISIBLE);
                else addGoalRegularBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(regularUserText.getText().toString().length() > 0)
                    addGoalRegularBtn.setVisibility(View.VISIBLE);
                else addGoalRegularBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        addGoalRegularBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        RegularEvent regularEventObj = new RegularEvent(regularUserText.getText().toString(), regularSpinner.getSelectedItem().toString());
                        if(!regularEditMode) {
                            MainActivity.regularList.add(regularEventObj);

                            Toast.makeText(getApplicationContext(),
                                    "Regular Goal created", Toast.LENGTH_LONG).show();
                        }
                        else {
                            regularEditMode = false;
                            MainActivity.regularList.set(regularPosition, regularEventObj);
                            Toast.makeText(getApplicationContext(),
                                    "Regular Goal edited", Toast.LENGTH_LONG).show();
                        }
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }
}
