package com.goalgitter.goalgitterroot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ///ArrayList<String> goalArrayList;
    private boolean state;
    ImageButton addGoalBtn;
    Button regularBtn;
    Button eventBtn;
    Button financialBtn;
    RecyclerView mainRecyclerView;
    String[] temp = new String[1];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the reference of ListViewAnimals
        //ListView goalListView = (ListView)findViewById(R.id.goalList);

        temp[0] = "First";
        state = false;
        addGoalBtn = (ImageButton) findViewById(R.id.addGoalButton);
        regularBtn = (Button) findViewById(R.id.regularButton);
        eventBtn = (Button) findViewById(R.id.eventButton);
        financialBtn = (Button) findViewById(R.id.financialButton);
        mainRecyclerView = (RecyclerView) findViewById(R.id.goalList);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mainRecyclerView.setAdapter(new Adapter(this,RegularEventActivity.regularEventList.get(0).getRegularGoal()));
        mainRecyclerView.setAdapter(new Adapter(this,temp));//temp

        addGoalBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if (!state) {
                            regularBtn.setVisibility(View.VISIBLE);
                            eventBtn.setVisibility(View.VISIBLE);
                            financialBtn.setVisibility(View.VISIBLE);
                            addGoalBtn.setImageResource(R.drawable.minus);
                            state = true;
                        } else if (state) {
                            regularBtn.setVisibility(View.INVISIBLE);
                            eventBtn.setVisibility(View.INVISIBLE);
                            financialBtn.setVisibility(View.INVISIBLE);
                            addGoalBtn.setImageResource(R.drawable.add);
                            state = false;
                        }
                    }
                }
        );

        regularBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), RegularEventActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
