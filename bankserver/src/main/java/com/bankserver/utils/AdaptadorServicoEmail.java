package com.bankserver.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bankserver.adapters.outbound.ports.EmailServicePort;

@Service
public class AdaptadorServicoEmail implements EmailServicePort {

    private final JavaMailSender javaMailSender;
    private final String sender;
    private static final Logger logger = LoggerFactory.getLogger(AdaptadorServicoEmail.class);

    // injecao via construtor, melhor pratica
    public AdaptadorServicoEmail(
            JavaMailSender javaMailSender,
            @Value("${spring.mail.username}") String sender) {
        this.javaMailSender = javaMailSender;
        this.sender = sender;
    }

    public String sendApproveEmail(String receiver, String subject, String message) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(receiver);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);
            javaMailSender.send(simpleMailMessage);
            logger.info("Email enviado para: {}", receiver);
            return "Email enviado";
        } catch (MailException e) {
            logger.error("Erro ao enviar email para {}: {}", receiver, e.getMessage(), e);
            return "Erro ao enviar email: " + e.getLocalizedMessage();
        }
    }

}
