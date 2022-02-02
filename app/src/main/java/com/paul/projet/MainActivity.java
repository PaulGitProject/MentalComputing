package com.paul.projet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        associateOpenActivityToButton(R.id.game_button, GameActivity.class);
        associateOpenActivityToButton(R.id.score_button, ScoreActivity.class);

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#957C6C"));

        // Set BackgroundDrawable
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(colorDrawable);
    }

    private void associateOpenActivityToButton(int id, Class activity){
        Button resultButton = findViewById(id);
        resultButton.setOnClickListener(view -> openActivity(activity));
    }

    private void openActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}