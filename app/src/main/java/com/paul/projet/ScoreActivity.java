package com.paul.projet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    public static final String FIRST = "FIRST";
    public static final String SECOND = "SECOND";
    public static final String OPERATOR = "OPERATOR";
    public static final String LASTRESULT = "LASTRESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.menu_color));

        // Set BackgroundDrawable
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", 0);
        int last_result = sharedPreferences.getInt(LASTRESULT, 0);
        int first = sharedPreferences.getInt(FIRST, 0);
        int second = sharedPreferences.getInt(SECOND, 0);
        String operator = sharedPreferences.getString(OPERATOR,"");

        TextView resultTextView = findViewById(R.id.last_question);

        String finalScoreLastCompute = first + " " + operator + " " + second + " = " + last_result;

        String text = getString(R.string.last_question_answer_message, finalScoreLastCompute);
        resultTextView.setText(text);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.scores_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.reset_button:
                //Reset scores
                break;
            case R.id.previous_score_menu_button:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}