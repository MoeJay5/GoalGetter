package com.goalgitter.goalgitterroot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class RegularEventActivity extends AppCompatActivity {

    public static ArrayList<RegularEvent> regularEventList = new ArrayList<>();
    EditText userText;
    String temp; //temp


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_event);

        userText = (EditText) findViewById(R.id.regularUserText);
        temp = "temp"; //temp

        final ImageButton addGoalRegularBtn = (ImageButton) findViewById(R.id.addGoalRegularButton);

        addGoalRegularBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {

                        RegularEvent regularEventObj = new RegularEvent(userText.getText().toString(), temp);
                        regularEventList.add(regularEventObj);

                        Toast.makeText(getApplicationContext(),
                                "Regular Goal created", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }
}