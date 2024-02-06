package com.msgfoundation.messages;

import com.msgfoundation.annotations.BPMNGetterVariables;
import com.msgfoundation.annotations.BPMNTask;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service("requestReject")
@BPMNTask(type="Send Task", name="Informar solicitud anulada por incumplimiento")
public class RequestRejectDelegateExpression implements JavaDelegate {
    @Override
    @BPMNGetterVariables(variables = {"coupleName1", "coupleName2", "quotaValue"})
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String coupleName1 = (String) delegateExecution.getProcessInstance().getVariables().get("coupleName1");
        String coupleName2 = (String) delegateExecution.getProcessInstance().getVariables().get("coupleName2");
        Long quotaValue = (Long) delegateExecution.getProcessInstance().getVariables().get("quotaValue");
        String codInstance = (String) delegateExecution.getProcessInstanceId();

        System.out.println("INFORME DE RECHAZO DE SOLICITUD\n" +
                "Estimados " + coupleName1 + " y " + coupleName2 + ", la solicitud de crédito ha sido rechazada por incoherencia en los soportes\n" +
                "el valor de la cuota (" + quotaValue + ") no concuerda con los documentos financieros. \n"+
                "Código de solicitud: "+ codInstance +
                "\nFecha de rechazo: "+ LocalDateTime.now());
    }
}
