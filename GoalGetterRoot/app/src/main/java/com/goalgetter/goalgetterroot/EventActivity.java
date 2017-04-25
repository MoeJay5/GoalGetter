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

public class EventActivity extends AppCompatActivity {

    private EditText eventName;
    private EditText eventGoal;
    private EditText userDate;
    private Spinner eventSpinner;

    private int eventUserVal = 0;
    private String eventNotifArray[] = {"None","Daily"};// Array of choices
    public static Boolean eventEditMode = false;
    public static int eventPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_goal);
        eventName = (EditText) findViewById(R.id.eventUserText);
        eventGoal = (EditText) findViewById(R.id.eventCurrentUserText);
        userDate = (EditText) findViewById(R.id.eventDays);
        eventSpinner = (Spinner) findViewById(R.id.eventSpinner);// Selection of the spinner

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,eventNotifArray);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        eventSpinner.setAdapter(spinnerArrayAdapter);

        final ImageButton addGoalEventBtn = (ImageButton) findViewById(R.id.addGoalEventButton);

        if(eventEditMode) { //Sets text and spinner to the what is being edited.
            eventName.setText(MainActivity.eventList.get(eventPosition).getEventName());
            eventGoal.setText(MainActivity.eventList.get(eventPosition).getEventGoal());
            userDate.setText(MainActivity.eventList.get(eventPosition).getEventDaysString());
            eventSpinner.setSelection(MainActivity.eventList.get(eventPosition).getEventNotifPos());
        }
        else addGoalEventBtn.setVisibility(View.INVISIBLE);// Set Invisible if user not editing

        eventName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(eventName.getText().toString().length() > 0 && eventGoal.getText().toString().length() > 0 && eventUserVal > 0)
                    addGoalEventBtn.setVisibility(View.VISIBLE);
                else addGoalEventBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(eventName.getText().toString().length() > 0 && eventGoal.getText().toString().length() > 0 && eventUserVal > 0)
                    addGoalEventBtn.setVisibility(View.VISIBLE);
                else addGoalEventBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        eventGoal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(eventName.getText().toString().length() > 0 && eventGoal.getText().toString().length() > 0 && eventUserVal > 0)
                    addGoalEventBtn.setVisibility(View.VISIBLE);
                else addGoalEventBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(eventName.getText().toString().length() > 0 && eventGoal.getText().toString().length() > 0 && eventUserVal > 0)
                    addGoalEventBtn.setVisibility(View.VISIBLE);
                else addGoalEventBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        userDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (userDate.getText().toString().length() > 0)
                    eventUserVal = Integer.parseInt(userDate.getText().toString());
                else
                    eventUserVal = 0;

                if(eventName.getText().toString().length() > 0 && eventGoal.getText().toString().length() > 0 && eventUserVal > 0)
                    addGoalEventBtn.setVisibility(View.VISIBLE);
                else addGoalEventBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (userDate.getText().toString().length() > 0)
                    eventUserVal = Integer.parseInt(userDate.getText().toString());
                else
                    eventUserVal = 0;

                if(eventName.getText().toString().length() > 0 && eventGoal.getText().toString().length() > 0 && eventUserVal > 0)
                    addGoalEventBtn.setVisibility(View.VISIBLE);
                else addGoalEventBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        addGoalEventBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        EventEvent eventEventObj = new EventEvent(eventName.getText().toString(), eventGoal.getText().toString(), Integer.parseInt(userDate.getText().toString()), eventSpinner.getSelectedItem().toString());
                        if(!eventEditMode) {
                            MainActivity.eventList.add(eventEventObj);

                            Toast.makeText(getApplicationContext(),
                                    "Event Goal created", Toast.LENGTH_LONG).show();
                        }
                        else {
                            eventEditMode = false;
                            MainActivity.eventList.set(eventPosition, eventEventObj);
                            Toast.makeText(getApplicationContext(),
                                    "Event Goal edited", Toast.LENGTH_LONG).show();
                        }
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }
}
