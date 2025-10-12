package com.bankserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "🚀 Bankserver API está funcionando! - " + java.time.LocalDateTime.now();
    }

    @GetMapping("/health")
    public String health() {
        return "✅ OK - Sistema operacional";
    }

    @GetMapping("/info")
    public String info() {
        return """
                Bankserver API
                Versão: 1.0
                Status: Online
                """;
    }
}
