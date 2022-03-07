package com.paul.mentalcounting.services;

import com.paul.mentalcounting.models.OperationModel;
import com.paul.mentalcounting.models.exceptions.DivideException;
import com.paul.mentalcounting.models.exceptions.OperatorException;
import com.paul.mentalcounting.models.exceptions.ResultException;

public class OperationService {

    public String computeResult(OperationModel operationModel) throws ResultException, DivideException, OperatorException {
        double firstValueAsDouble,secondValueAsDouble = 0;
        double result = 0;

        String operator = operationModel.getOperator();

        try{
            firstValueAsDouble = Double.parseDouble(operationModel.getFirstValue());
            secondValueAsDouble = Double.parseDouble(operationModel.getSecondValue());
        }catch (NumberFormatException e){
            throw new ResultException("Values are not number", e);
        }



        switch (operator){
            case "+":
                result = firstValueAsDouble + secondValueAsDouble;
                break;
            case "-":
                result = firstValueAsDouble - secondValueAsDouble;
                break;
            case "*":
                result = firstValueAsDouble * secondValueAsDouble;
                break;
            case "/":
                if(secondValueAsDouble == 0){
                    throw new DivideException("Can't divide by 0");
                }
                result = firstValueAsDouble / secondValueAsDouble;
                break;
            default:
                throw new OperatorException("Invalid operator");
        }
    }
}
