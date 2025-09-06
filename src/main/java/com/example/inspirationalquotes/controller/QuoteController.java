package com.example.inspirationalquotes.controller;

import com.example.inspirationalquotes.service.QuoteService;
import com.example.inspirationalquotes.util.RateLimiter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class QuoteController {
    private final QuoteService quoteService;
    private final RateLimiter rateLimiter;

    public QuoteController(QuoteService quoteService, RateLimiter rateLimiter) {
        this.quoteService = quoteService;
        this.rateLimiter = rateLimiter;
    }

    @GetMapping("/")
    public ResponseEntity<?> getQuote(HttpServletRequest request) {
        String ip = request.getRemoteAddr();

        if (!rateLimiter.isAllowed(ip)) {
            long retryAfter = rateLimiter.getRetryAfter(ip);
            log.warn("IP {} exceeded rate limit", ip);
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(Map.of("error", "Rate limit exceeded. Try again in " + retryAfter + " seconds."));
        }

        String quote = quoteService.getRandomQuote();
        log.info("IP {} received quote: {}", ip, quote);
        return ResponseEntity.ok(Map.of("quote", quote));
    }
}
