package com.paul.projet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.paul.projet.models.OperationModel;
import com.paul.projet.services.OperationService;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.menu_color));

        // Set BackgroundDrawable
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        OperationService operationService = new OperationService();
        OperationModel operationModel = operationService.generateRandomOperation();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.score_menu_button:
                Intent intent = new Intent(this,ScoreActivity.class);
                startActivity(intent);
                break;
            case R.id.previous_game_menu_button:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}