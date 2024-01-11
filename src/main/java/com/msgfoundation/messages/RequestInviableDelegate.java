package com.msgfoundation.messages;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class RequestInviableDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("INFORME DE INVIABILIDAD FINANCIERA DE CRÉDITO\n" +
                "La solicitud de crédito es financieramente inválida\n" +
                "Código de solicitud: "+delegateExecution.getProcessInstanceId() +
                "\nFecha de rechazo: "+"Hoy :D");
    }
}
