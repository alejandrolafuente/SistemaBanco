package com.bankserver.adapters.outbound.ports;

public interface EmailServicePort {
    
    String sendApproveEmail(String receiver, String subject, String message);
}
