package com.msgfoundation.messages;

import com.msgfoundation.annotations.BPMNGetterVariables;
import com.msgfoundation.annotations.BPMNTask;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.time.LocalDateTime;

@BPMNTask(type="Send Task", name="Informar concepto jurídico no viable")
public class RequestInviableDelegate implements JavaDelegate {
    @Override
    @BPMNGetterVariables(variables = {"coupleName1", "coupleName2", "quotaValue"})
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String coupleName1 = (String) delegateExecution.getProcessInstance().getVariables().get("coupleName1");
        String coupleName2 = (String) delegateExecution.getProcessInstance().getVariables().get("coupleName2");
        Long marriageYears = (Long) delegateExecution.getProcessInstance().getVariables().get("marriageYears");
        String codInstance = (String) delegateExecution.getProcessInstanceId();


        System.out.println("INFORME DE INVIABILIDAD FINANCIERA DE CRÉDITO\n" +
                "Estimados " + coupleName1 + " y " + coupleName2 + ", la solicitud de crédito es financieramente inválida,\n" +
                "los años de casados registrados (" + marriageYears +") no cumplen las politicas de viabilidad. \n" +
                "Código de solicitud: "+ codInstance +
                "\nFecha de rechazo: "+ LocalDateTime.now());
    }
}
