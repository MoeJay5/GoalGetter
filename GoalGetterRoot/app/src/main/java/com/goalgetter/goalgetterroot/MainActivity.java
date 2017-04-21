package com.goalgetter.goalgetterroot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ///ArrayList<String> goalArrayList;
    private boolean state;
    ImageButton addGoalBtn;
    Button regularBtn;
    Button eventBtn;
    Button financialBtn;
    RecyclerView mainRecyclerView;
    static ArrayList<RegularEvent> list_something = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the reference of ListViewAnimals
        //ListView goalListView = (ListView)findViewById(R.id.goalList);

        state = false;
//        RegularEvent lol = new RegularEvent("Hey Moe","Hey");
//        list_something.add(lol);
        addGoalBtn = (ImageButton) findViewById(R.id.addGoalButton);
        regularBtn = (Button) findViewById(R.id.regularButton);
        eventBtn = (Button) findViewById(R.id.eventButton);
        financialBtn = (Button) findViewById(R.id.financialButton);
        mainRecyclerView = (RecyclerView) findViewById(R.id.regularGoalList);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RegularAdapter regularAdapter = new RegularAdapter(this,list_something);
        //mainRecyclerView.setAdapter(new Adapter(this,RegularEventActivity.regularEventList.get(0).getRegularGoal()));
        mainRecyclerView.setAdapter(regularAdapter);

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
//    public static ArrayList<RegularEvent> setEvents(ArrayList<RegularEvent> list){ //Not Needed?
//        list  = list_something;
//        return list;
//    }
}
