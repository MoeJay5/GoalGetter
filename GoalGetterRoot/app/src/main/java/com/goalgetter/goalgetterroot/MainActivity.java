package com.goalgetter.goalgetterroot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private boolean state;
    private ImageButton addGoalBtn;
    private Button regularBtn;
    private Button eventBtn;
    private Button financialBtn;
    public static TextView regularTitle;
    public static TextView financialTitle;
    public static TextView eventTitle;

    public static ArrayList<RegularEvent> regularList = new ArrayList<>();
    public static ArrayList<FinancialEvent> financialList = new ArrayList<>();
    public static ArrayList<EventEvent> eventList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        state = false;
        addGoalBtn = (ImageButton) findViewById(R.id.addGoalButton);

        regularTitle = (TextView)  findViewById(R.id.regularText);
        regularBtn = (Button) findViewById(R.id.regularButton);
        RecyclerView regularRecyclerView = (RecyclerView) findViewById(R.id.regularGoalList);
        regularRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RegularAdapter regularAdapter = new RegularAdapter(this,regularList);
        regularRecyclerView.setAdapter(regularAdapter);

        financialTitle = (TextView)  findViewById(R.id.financialText);
        financialBtn = (Button) findViewById(R.id.financialButton);
        RecyclerView financialRecyclerView = (RecyclerView) findViewById(R.id.financialGoalList);
        financialRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FinancialAdapter financialAdapter = new FinancialAdapter(this,financialList);
        financialRecyclerView.setAdapter(financialAdapter);

        eventTitle = (TextView)  findViewById(R.id.eventText);
        eventBtn = (Button) findViewById(R.id.eventButton);
        RecyclerView eventRecyclerView = (RecyclerView) findViewById(R.id.eventGoalList);
        eventRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EventAdapter eventAdapter = new EventAdapter(this,eventList);
        eventRecyclerView.setAdapter(eventAdapter);

        if (regularList.size() > 0)
            regularTitle.setVisibility(View.VISIBLE);
        if (financialList.size() > 0)
            financialTitle.setVisibility(View.VISIBLE);
        if (eventList.size() > 0)
            eventTitle.setVisibility(View.VISIBLE);

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
                        Intent intent = new Intent(getApplicationContext(), RegularActivity.class);
                        startActivity(intent);
                    }
                }
        );
        financialBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), FinancialActivity.class);
                        startActivity(intent);
                    }
                }
        );
        eventBtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
