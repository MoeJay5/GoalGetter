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

import static com.goalgetter.goalgetterroot.MainActivity.regularList;

public class RegularActivity extends AppCompatActivity {

    //ArrayList<RegularEvent> regularEventList = new ArrayList<>();
    EditText regularUserText;
    Spinner regularSpinner;
    public static Boolean regularEditMode = false;
    public static int regularPosition = 0;
    String regularNotifArray[] = {"None","Daily","Weekly","Monthly"};// Array of choices


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

        if(regularEditMode == true) { //Sets text and spinner to the what is being edited.
            regularUserText.setText(regularList.get(regularPosition).getRegularGoal());
            regularSpinner.setSelection(regularList.get(regularPosition).getRegularNotifPos());
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
                        if(regularEditMode == false) {
                            regularList.add(regularEventObj);
                            //regularEventList.add(regularEventObj); //Not Needed?
                            //MainActivity.setEvents(regularEventList); //Not Needed?

                            Toast.makeText(getApplicationContext(),
                                    "Regular Goal created", Toast.LENGTH_LONG).show();
                        }
                        else {
                            regularEditMode = false;
                            regularList.set(regularPosition, regularEventObj);
                            //MainActivity.setEvents(regularEventList); //Not Needed?
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
