package com.goalgitter.goalgitterroot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import static com.goalgitter.goalgitterroot.MainActivity.*;
import java.util.ArrayList;

public class RegularEventActivity extends AppCompatActivity {

    ArrayList<RegularEvent> regularEventList = new ArrayList<>();
    EditText userText;
    public static Boolean editMode = false;
    public static int regularPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_goal);

        userText = (EditText) findViewById(R.id.regularUserText);

        final ImageButton addGoalRegularBtn = (ImageButton) findViewById(R.id.addGoalRegularButton);

        addGoalRegularBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        RegularEvent regularEventObj = new RegularEvent(userText.getText().toString(), "pass");
                        if(editMode == false) {
                            list_something.add(regularEventObj);
                            //regularEventList.add(regularEventObj); //Not Needed?
                            //MainActivity.setEvents(regularEventList); //Not Needed?

                            Toast.makeText(getApplicationContext(),
                                    "Regular Goal created", Toast.LENGTH_LONG).show();
                        }
                        else {
                            editMode = false;
                            list_something.set(regularPosition, regularEventObj);
                            //MainActivity.setEvents(regularEventList); //Not Needed?
                            Toast.makeText(getApplicationContext(),
                                    "Regular Goal edited", Toast.LENGTH_LONG).show();
                        }
                        Intent intent = new Intent(RegularEventActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }
}
