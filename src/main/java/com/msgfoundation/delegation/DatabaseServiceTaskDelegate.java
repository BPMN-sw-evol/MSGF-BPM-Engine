package com.msgfoundation.delegation;

import com.msgfoundation.anotaciones.BPMNTask;
import com.msgfoundation.anotaciones.GetVariable;
import com.msgfoundation.anotaciones.SetVariables;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@BPMNTask({"type=ServiceTask", "name=Consultar información financiera"})
public class DatabaseServiceTaskDelegate implements JavaDelegate {

    @GetVariable("codRequest")
    private Long codRequest;

    @SetVariables({"coupleSavings","housePrices","quotaValue"})
    private void updateVariables(DelegateExecution execution, long coupleSavings, long housesPrices, long quotaValue){
        System.out.println("Couple Savings: " + coupleSavings);
        System.out.println("Houses Prices: " + housesPrices);
        System.out.println("Quota Value: " + quotaValue);

        execution.setVariable("coupleSavings",coupleSavings);
        execution.setVariable("housePrices",housesPrices);
        execution.setVariable("quotaValue",quotaValue);
    }
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        codRequest = (Long) execution.getProcessInstance().getVariables().get("codRequest");
        if (codRequest != null) {
            try {
                // Establecer la conexión a la base de datos PostgreSQL
                Connection connection = DriverManager.getConnection("jdbc:postgresql://rds-msgf.cyrlczakjihy.us-east-1.rds.amazonaws.com:5432/credit_request", "postgres", "msgfoundation");

                // Crear una declaración preparada
                String query = "SELECT couple_savings, house_prices, quota_value FROM credit_request WHERE cod_request = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setLong(1, codRequest);

                // Ejecutar la consulta en la base de datos
                ResultSet resultSet = preparedStatement.executeQuery();

                long coupleSavings = resultSet.getLong("couple_savings");
                long housesPrices = resultSet.getLong("house_prices");
                long quotaValue = resultSet.getLong("quota_value");

                //@SetVariables implementation
                updateVariables(execution,coupleSavings,housesPrices,quotaValue);

                // Cerrar la conexión
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Si no se encuentra la variable del proceso "codRequest"
            System.out.println("La variable codRequest no está definida o es nula.");
        }
    }
}
