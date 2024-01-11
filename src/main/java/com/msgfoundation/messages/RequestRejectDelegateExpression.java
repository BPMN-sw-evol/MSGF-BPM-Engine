package com.msgfoundation.messages;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("requestReject")
public class RequestRejectDelegateExpression implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("INFORME DE RECHAZO DE SOLICITUD\n" +
                "La solicitud de crédito ha sido rechazada por incumplimiento en los soportes\n" +
                "Código de solicitud: "+delegateExecution.getProcessInstanceId() +
                "\nFecha de rechazo: "+"Hoy :D");
    }
}
