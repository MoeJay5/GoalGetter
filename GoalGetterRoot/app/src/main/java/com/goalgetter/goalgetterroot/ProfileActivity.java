package com.goalgetter.goalgetterroot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by moeja on 4/22/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    public static Button profile1;
    public static Button profile2;
    public static Button profile3;
    ImageButton profile1Edit;
    ImageButton profile2Edit;
    ImageButton profile3Edit;
    public static int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selection);

        profile1 = (Button) findViewById(R.id.profileButton1);
        profile2 = (Button) findViewById(R.id.profileButton2);
        profile3 = (Button) findViewById(R.id.profileButton3);
        profile1Edit = (ImageButton) findViewById(R.id.editButton1);
        profile2Edit = (ImageButton) findViewById(R.id.editButton2);
        profile3Edit = (ImageButton) findViewById(R.id.editButton3);

        if (state == 1)
            profile1.setText(ProfileEvent.profileUserText.getText());
        else if (state == 2)
            profile2.setText(ProfileEvent.profileUserText.getText());
        else if (state == 3)
            profile3.setText(ProfileEvent.profileUserText.getText());

        profile1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
        profile2.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),  MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
        profile3.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),  MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

        profile1Edit.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        state = 1;
                        Intent intent = new Intent(getApplicationContext(),  ProfileEvent.class);
                        startActivity(intent);
                    }
                }
        );
        profile2Edit.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        state = 2;
                        Intent intent = new Intent(getApplicationContext(),  ProfileEvent.class);
                        startActivity(intent);
                    }
                }
        );
        profile3Edit.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        state = 3;
                        Intent intent = new Intent(getApplicationContext(),  ProfileEvent.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
