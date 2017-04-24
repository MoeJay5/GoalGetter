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

import static com.goalgetter.goalgetterroot.MainActivity.financialList;

public class FinancialActivity extends AppCompatActivity {

    //ArrayList<FinancialEvent> financialEventList = new ArrayList<>();
    EditText financialUserText;
    EditText financialCurrentUserText;
    Spinner financialSpinner;

    private double financialGoal = 0;
    private double financialCurrentGoal = 0;

    public static Boolean editMode = false;
    public static int financialPosition = 0;
    String financialNotifArray[] = {"Daily","Weekly","Monthly"};// Array of choices


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

        if(editMode == true) { //Sets text and spinner to the what is being edited.
            financialUserText.setText(financialList.get(financialPosition).getFinancialGoal());
            financialCurrentUserText.setText(financialList.get(financialPosition).getFinancialCurrentGoal());
            financialSpinner.setSelection(financialList.get(financialPosition).getFinancialNotifPos());
        }
        else addGoalFinancialBtn.setVisibility(View.INVISIBLE);// Set Invisible if user not editing

        financialUserText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (editMode && financialUserText.getText().toString().length() > 0)
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
                System.out.println(financialUserText.getText().toString());
                if (editMode && financialCurrentUserText.getText().toString().length() > 0)
                    financialCurrentGoal = Double.parseDouble(financialCurrentUserText.getText().toString());
                else
                    financialCurrentGoal = 0;

                if(financialGoal > 0 && financialCurrentGoal > 0 && financialGoal - financialCurrentGoal > 0)
                    addGoalFinancialBtn.setVisibility(View.VISIBLE);
                else addGoalFinancialBtn.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println(financialCurrentUserText.getText().toString());
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
                        if(editMode == false) {
                            financialList.add(financialEventObj);
                            //financialEventList.add(financialEventObj); //Not Needed?
                            //MainActivity.setEvents(financialEventList); //Not Needed?

                            Toast.makeText(getApplicationContext(),
                                    "Financial Goal created", Toast.LENGTH_LONG).show();
                        }
                        else {
                            editMode = false;
                            financialList.set(financialPosition, financialEventObj);
                            //MainActivity.setEvents(financialEventList); //Not Needed?
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
