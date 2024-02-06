package com.msgfoundation.messages;

import com.msgfoundation.annotations.BPMNGetterVariables;
import com.msgfoundation.annotations.BPMNTask;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.time.LocalDateTime;

@BPMNTask(type="Send Task", name="Informar inconsistencias")
public class InconsistenciesDelegate implements JavaDelegate {
    @Override
    @BPMNGetterVariables(variables = {"coupleName1", "coupleName2", "creationDate"})
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String coupleName1 = (String) delegateExecution.getProcessInstance().getVariables().get("coupleName1");
        String coupleName2 = (String) delegateExecution.getProcessInstance().getVariables().get("coupleName2");
        String creationDate = (String) delegateExecution.getProcessInstance().getVariables().get("creationDate");
        String codInstance = (String) delegateExecution.getProcessInstanceId();

        System.out.println("INFORME DE INCONSISTENCIAS EN SOLICITUD\n" +
                "Estimados " + coupleName1 + " y " + coupleName2 + ", la solicitud de crédito realiazada el dia " +creationDate + "ha sido rechazada por inconsistencias en los requisitos.\n" +
                "Código de solicitud: "+ codInstance +
                "\nFecha de rechazo: "+ LocalDateTime.now());

    }
}
