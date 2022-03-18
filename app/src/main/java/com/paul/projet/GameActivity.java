package com.paul.projet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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
    OperationService operationService;

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
        operationService = new OperationService();

        generateRandomCalculation();

        Button validateResult = findViewById(R.id.validate_button);

        validateResult.setOnClickListener(view -> {
            onValidateClick();
        });
    }

    private void generateRandomCalculation(){
        operationModel = operationService.generateRandomOperation();

        String operation = getString(R.string.operation_template, operationModel.getFirstValue(), operationModel.getOperator(), operationModel.getSecondValue());
        this.operationTextView.setText(operation);
    }

    private void onValidateClick() {
        try {
            String textResult = resultEditText.getText().toString();
            verifyResult(textResult);
        }catch (OperatorException operatorException){
            operatorException.printStackTrace();
        }
    }



    private void verifyResult(String resultText) throws OperatorException {
            VerifyUserResultService verifyUserResultService = new VerifyUserResultService();

            TextView operatorTextViewCorrect = findViewById(R.id.correct_result_text);
            TextView operatorTextViewIncorrect = findViewById(R.id.incorrect_result_text);
            TextView operatorTextViewEmpty = findViewById(R.id.empty_result_text);

            try {
                int result = Integer.parseInt(resultText);
                boolean verifyResult = verifyUserResultService.verifyComputeResult(operationModel, result);

                if(verifyResult){
                    setTextVisibility(operatorTextViewIncorrect, operatorTextViewEmpty, operatorTextViewCorrect);
                    generateRandomCalculation();

                }else{
                    setTextVisibility(operatorTextViewEmpty, operatorTextViewCorrect, operatorTextViewIncorrect);
                }
            }catch (NumberFormatException e){
                setTextVisibility(operatorTextViewCorrect, operatorTextViewIncorrect, operatorTextViewEmpty);
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

    private void setTextVisibility(TextView toBeGoneFirst, TextView toBeGoneSecond, TextView toBeVisible){
        toBeGoneFirst.setVisibility(View.GONE);
        toBeGoneSecond.setVisibility(View.GONE);
        toBeVisible.setVisibility(View.VISIBLE);
    }
}