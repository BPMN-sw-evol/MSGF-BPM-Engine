package com.msgfoundation.delegation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DatabaseServiceTaskDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String processId = execution.getProcessInstanceId();
        System.out.println(execution.getProcessInstanceId());
        try {
            // Establecer la conexión a la base de datos PostgreSQL
            Connection connection = DriverManager.getConnection("jdbc:postgresql://rds-msgf.cyrlczakjihy.us-east-1.rds.amazonaws.com:5432/credit_request", "postgres", "msgfoundation");

            // Crear una declaración preparada
            String query = "SELECT couple_savings, house_prices, quota_value FROM credit_request WHERE process_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, processId);

            // Ejecutar la consulta en la base de datos
            ResultSet resultSet = preparedStatement.executeQuery();

            // Procesar los resultados
            while (resultSet.next()) {
                long coupleSavings = resultSet.getLong("couple_savings");
                long housesPrices = resultSet.getLong("house_prices");
                long quotaValue = resultSet.getLong("quota_value");

                // Realizar cualquier operación adicional aquí con los resultados obtenidos
                System.out.println("Couple Savings: " + coupleSavings);
                System.out.println("Houses Prices: " + housesPrices);
                System.out.println("Quota Value: " + quotaValue);

                execution.setVariable("coupleSavings",coupleSavings);
                execution.setVariable("housePrices",housesPrices);
                execution.setVariable("quotaValue",quotaValue);
            }

            // Cerrar la conexión
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
