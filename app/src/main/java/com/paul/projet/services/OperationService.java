package com.paul.projet.services;

import com.paul.projet.models.OperationModel;

import java.util.Random;

public class OperationService {

    public OperationModel generateRandomOperation() {
        return new OperationModel(generateRandomInteger(),generateRandomInteger(), generateRandomOperator());
    }

    private int generateRandomInteger(){
        int maxValue = 100;

        return new Random().nextInt(maxValue);
    }

    private String generateRandomOperator(){
        String[] operators = {"+","-","*"};

        return operators[new Random().nextInt(operators.length)];
    }
}
