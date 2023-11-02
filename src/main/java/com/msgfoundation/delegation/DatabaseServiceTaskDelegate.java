package com.msgfoundation.delegation;

import com.msgfoundation.annotations.*;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.sql.*;

@BPMNTask(type="ServiceTask", name="Consultar información financiera")
public class DatabaseServiceTaskDelegate implements JavaDelegate {

    @BPMNGetVariables(variables="codRequest")
    private Long codRequest;

//    @BPMNSetVariables(variables = {"coupleSavings","housePrices","quotaValue"})
//    private String[] variables2Set;
    @BPMNSetVariables(variables = "coupleSavings")
    private Long coupleSavings;

    @BPMNSetVariables(variables = "housePrices")
    private Long housePrices;

    @BPMNSetVariables(variables = "quotaValue")
    private Long quotaValue;

    @BPMNGetterVariables
    public ResultSet getterVariables(Long codRequest) throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:postgresql://rds-msgf.cyrlczakjihy.us-east-1.rds.amazonaws.com:5432/credit_request", "postgres", "msgfoundation");

        String query = "SELECT couple_savings, house_prices, quota_value FROM credit_request WHERE cod_request = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, codRequest);

        return preparedStatement.executeQuery();
    }

    @BPMNSetterVariables
    public void setterVariables(DelegateExecution execution, ResultSet resultSet) throws SQLException {
        if(resultSet.next()){
            long coupleSavings = resultSet.getLong("couple_savings");
            long housesPrices = resultSet.getLong("house_prices");
            long quotaValue = resultSet.getLong("quota_value");

            System.out.println("Couple Savings: " + coupleSavings);
            System.out.println("Houses Prices: " + housesPrices);
            System.out.println("Quota Value: " + quotaValue);

            execution.setVariable("coupleSavings",coupleSavings);
            execution.setVariable("housePrices",housesPrices);
            execution.setVariable("quotaValue",quotaValue);
        }
    }
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        codRequest = (Long) execution.getProcessInstance().getVariables().get("codRequest");
        if (codRequest != null) {
            ResultSet resultSet = getterVariables(codRequest);
            setterVariables(execution,resultSet);
        } else {
            System.out.println("La variable codRequest no está definida o es nula.");
        }
    }
}
