package com.msgfoundation.messages;

import com.msgfoundation.annotations.BPMNGetterVariables;
import com.msgfoundation.annotations.BPMNTask;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.time.LocalDateTime;


import java.time.LocalDateTime;

@BPMNTask(type="Send Task", name="Informar rechazo de solicitud")
public class RequestRejectByCommitteeDelegate implements JavaDelegate {
    @Override
    @BPMNGetterVariables(variables = {"coupleName1", "coupleName2", "housePrice", "coupleSaving"})
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String coupleName1 = (String) delegateExecution.getProcessInstance().getVariables().get("coupleName1");
        String coupleName2 = (String) delegateExecution.getProcessInstance().getVariables().get("coupleName2");
        Long housePrice = (Long) delegateExecution.getProcessInstance().getVariables().get("housePrice");
        Long coupleSaving = (Long) delegateExecution.getProcessInstance().getVariables().get("coupleSavings");
        String codInstance = (String) delegateExecution.getProcessInstanceId();

        System.out.println("INFORME DE RECHAZO POR PARTE DEL COMITÉ DE CRÉDITO\n" +
                "Estimados " + coupleName1 + " y " + coupleName2 + ", la solicitud de crédito ha sido es rechazada por concepto del comité,\n" +
                "el costo de la vivienda (" + housePrice + ") es demasido alto y los ahorros registrados (" + coupleSaving + ") no cumplen con la normatividad. \n"+
                "Código de solicitud: "+ codInstance +
                "\nFecha de rechazo: "+ LocalDateTime.now());
    }
}
