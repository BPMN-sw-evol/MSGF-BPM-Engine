package com.msgfoundation.delegation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service("activateForm")
public class ActivateFormDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long codRequest = (Long) delegateExecution.getProcessInstance().getVariables().get("codRequest");
        if (codRequest != null) {
            updateStatusToDraft(codRequest); 
        }else{
            System.out.println("CodRequest is not defined or is invalid");
        }
    }

    private void updateStatusToDraft(Long codRequest) throws SQLException {
        System.out.println("Your credit status has been updated");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/credit_request", "postgres", "admin");

        String updateQuery = "UPDATE credit_request SET status = 'DRAFT' WHERE cod_request = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setLong(1, codRequest);
            updateStatement.executeUpdate();
        }
    }
}
