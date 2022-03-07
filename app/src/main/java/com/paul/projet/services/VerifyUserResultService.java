package com.paul.projet.services;

import com.paul.projet.models.OperationModel;
import com.paul.projet.models.exceptions.OperatorException;

public class VerifyUserResultService {

    public boolean verifyComputeResult(OperationModel operationModel, int userResult) throws OperatorException {
        int computeResult = computeResult(operationModel);

        return computeResult == userResult;
    }

    public int computeResult(OperationModel operationModel) throws OperatorException {
        int firstValueAsDouble, secondValueAsDouble = 0;
        int result = 0;

        String operator = operationModel.getOperator();

        firstValueAsDouble = operationModel.getFirstValue();
        secondValueAsDouble = operationModel.getSecondValue();


        switch (operator) {
            case "+":
                result = firstValueAsDouble + secondValueAsDouble;
                break;
            case "-":
                result = firstValueAsDouble - secondValueAsDouble;
                break;
            case "*":
                result = firstValueAsDouble * secondValueAsDouble;
                break;
            default:
                throw new OperatorException("Invalid operator");
        }

        return result;
    }
}
