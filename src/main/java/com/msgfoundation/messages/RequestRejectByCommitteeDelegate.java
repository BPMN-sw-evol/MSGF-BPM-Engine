package com.msgfoundation.messages;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class RequestRejectByCommitteeDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("INFORME DE RECHAZO POR PARTE DEL COMITÉ DE CRÉDITO\n" +
                "La solicitud de crédito ha sido es rechazada por concepto del comité\n" +
                "Código de solicitud: "+delegateExecution.getProcessInstanceId() +
                "\nFecha de rechazo: "+"Hoy :D");
    }
}
