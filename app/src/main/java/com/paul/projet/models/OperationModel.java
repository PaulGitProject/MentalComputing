package com.paul.mentalcounting.models;

public class OperationModel {

    private String firstValue;
    private String secondValue;
    private String operator;


    public OperationModel(String first, String second, String operator){
        this.firstValue = first;
        this.secondValue = second;
        this.operator = operator;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public String getOperator() {
        return operator;
    }
}
