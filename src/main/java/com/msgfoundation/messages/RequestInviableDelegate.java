package com.msgfoundation.messages;

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
public class RequestInviableDelegate implements JavaDelegate {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        // Obtener variables del proceso
        String processID = (String) delegateExecution.getProcessInstanceId();
        String coupleName1 = (String) delegateExecution.getVariable("coupleName1");
        String coupleName2 = (String) delegateExecution.getVariable("coupleName2");
        String coupleEmail1 = (String) delegateExecution.getVariable("coupleEmail1");
        String coupleEmail2 = (String) delegateExecution.getVariable("coupleEmail2");
        String coupleSavings = (String) delegateExecution.getVariable("coupleSavings");
        String quotaValue = (String) delegateExecution.getVariable("quotaValue");
        String housePrices = (String) delegateExecution.getVariable("housePrices");

        // Construir el mensaje de correo electrónico usando Thymeleaf
        String subject = "Informe de Rechazo por Oficina Legal - Inviabilidad";
        String templateName = "InformeRechazoLegalInviable";
        Context context = new Context(Locale.getDefault());
        context.setVariable("processId",processID);
        context.setVariable("coupleName1", coupleName1);
        context.setVariable("coupleName2", coupleName2);
        context.setVariable("coupleEmail1", coupleEmail1);
        context.setVariable("coupleEmail2", coupleEmail2);
        context.setVariable("coupleSavings", coupleSavings);
        context.setVariable("quotaValue", quotaValue);
        context.setVariable("housePrices", housePrices);

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
    }
}
