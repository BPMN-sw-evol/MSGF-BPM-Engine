package com.msgfoundation.messages;

import com.msgfoundation.annotations.BPMNGetterVariables;
import com.msgfoundation.annotations.BPMNTask;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Component
import java.time.LocalDateTime;


import java.time.LocalDateTime;

@BPMNTask(type="Send Task", name="Informar rechazo de solicitud")
public class RequestRejectByCommitteeDelegate implements JavaDelegate {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Override
    @BPMNGetterVariables(variables = {"coupleName1", "coupleName2", "housePrice", "coupleSaving"})
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // Obtener variables del proceso
        String processID = delegateExecution.getProcessInstanceId();
        String coupleName1 = (String) delegateExecution.getVariable("coupleName1");
        String coupleName2 = (String) delegateExecution.getVariable("coupleName2");
        String coupleEmail1 = (String) delegateExecution.getVariable("coupleEmail1");
        String coupleEmail2 = (String) delegateExecution.getVariable("coupleEmail2");
        Boolean bothEmployees = (Boolean) delegateExecution.getVariable("bothEmployees");
        String pdfSupport = (String) delegateExecution.getVariable("pdfSupport");


        // Construir el mensaje de correo electrónico usando Thymeleaf
        String subject = "Informe de Rechazo por Comité de Crédito";
        String templateName = "InformeRechazoComite";
        Context context = new Context(Locale.getDefault());
        context.setVariable("processId",processID);
        context.setVariable("coupleName1", coupleName1);
        context.setVariable("coupleName2", coupleName2);
        context.setVariable("coupleEmail1", coupleEmail1);
        context.setVariable("coupleEmail2", coupleEmail2);
        context.setVariable("bothEmployees", bothEmployees);
        context.setVariable("pdfSupport", pdfSupport);

        String message = templateEngine.process(templateName, context);

        // Enviar el correo electrónico
        sendEmail(new String[]{coupleEmail1, coupleEmail2}, subject, message);
    }


    private void sendEmail(String[] to, String subject, String body) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

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
