package com.msgfoundation.messages;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class InconsistenciesDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("INFORME DE RECHAZO DE SOLICITUD\n" +
                "La solicitud de crédito ha sido rechazada por inconsistencias en los requisitos\n" +
                "Código de solicitud: "+delegateExecution.getProcessInstanceId() +
                "\nFecha de rechazo: "+"Hoy :D");
    }
}
