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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.paul.projet.models.OperationModel;
import com.paul.projet.models.exceptions.OperatorException;
import com.paul.projet.services.OperationService;
import com.paul.projet.services.VerifyUserResultService;

public class GameActivity extends AppCompatActivity {

    private TextView operationTextView;
    private EditText resultEditText;
    private OperationModel operationModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.menu_color));

        // Set BackgroundDrawable
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        resultEditText = findViewById(R.id.answer_input);
        operationTextView = findViewById(R.id.calculation_text);

        OperationService operationService = new OperationService();
        operationModel = operationService.generateRandomOperation();

        String calcul = operationModel.getFirstValue() + " " + operationModel.getOperator() + " " + operationModel.getSecondValue();
        operationTextView.setText(calcul);

        Button validateResult = findViewById(R.id.validate_button);

        validateResult.setOnClickListener(view -> {
            try {
                onValidateClick();
            } catch (OperatorException e) {
                e.printStackTrace();
            }
        });
    }

    private void onValidateClick() throws OperatorException {
        int result = Integer.parseInt(resultEditText.getText().toString());
        verifyResult(result);
    }



    private void verifyResult(int result) throws OperatorException {
            VerifyUserResultService verifyUserResultService = new VerifyUserResultService();

            boolean verifyResult = verifyUserResultService.verifyComputeResult(operationModel, result);

            if(verifyResult){
                TextView operatorTextViewCorrect = findViewById(R.id.correct_result_text);
                operatorTextViewCorrect.setVisibility(View.VISIBLE);
                TextView operatorTextViewIncorrect = findViewById(R.id.incorrect_result_text);
                operatorTextViewIncorrect.setVisibility(View.GONE);
                this.resultEditText.setVisibility(View.GONE);
            }else{
                TextView operatorTextViewIncorrect = findViewById(R.id.incorrect_result_text);
                operatorTextViewIncorrect.setVisibility(View.VISIBLE);
            }
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