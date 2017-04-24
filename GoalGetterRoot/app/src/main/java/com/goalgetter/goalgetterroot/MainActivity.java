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

    ///ArrayList<String> goalArrayList;
    private boolean state;
    ImageButton addGoalBtn;
    Button regularBtn;
    Button eventBtn;
    Button financialBtn;
    RecyclerView regularRecyclerView;
    RecyclerView financialRecyclerView;
    public static TextView regularTitle;
    public static TextView financialTitle;
    public static ArrayList<RegularEvent> regularList = new ArrayList<>();
    public static ArrayList<FinancialEvent> financialList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the reference of ListViewAnimals
        //ListView goalListView = (ListView)findViewById(R.id.goalList);

        state = false;
        addGoalBtn = (ImageButton) findViewById(R.id.addGoalButton);

        regularTitle = (TextView)  findViewById(R.id.regularText);
        regularBtn = (Button) findViewById(R.id.regularButton);
        regularRecyclerView = (RecyclerView) findViewById(R.id.regularGoalList);
        regularRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RegularAdapter regularAdapter = new RegularAdapter(this,regularList);
        regularRecyclerView.setAdapter(regularAdapter);

        financialTitle = (TextView)  findViewById(R.id.financialText);
        financialBtn = (Button) findViewById(R.id.financialButton);
        financialRecyclerView = (RecyclerView) findViewById(R.id.financialGoalList);
        financialRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FinancialAdapter financialAdapter = new FinancialAdapter(this,financialList);
        financialRecyclerView.setAdapter(financialAdapter);

        eventBtn = (Button) findViewById(R.id.eventButton);

        //mainRecyclerView.setAdapter(new Adapter(this,RegularEventActivity.regularEventList.get(0).getRegularGoal()));

        if (regularList.size() > 0)
            regularTitle.setVisibility(View.VISIBLE);
        if (financialList.size() > 0)
            financialTitle.setVisibility(View.VISIBLE);

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
    }
//    public static ArrayList<RegularEvent> setEvents(ArrayList<RegularEvent> list){ //Not Needed?
//        list  = regularList;
//        return list;
//    }
}
