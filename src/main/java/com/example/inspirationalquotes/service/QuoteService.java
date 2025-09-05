package com.example.inspirationalquotes.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class QuoteService {
    private final List<String> quotes = List.of(
            "The only way to do great work is to love what you do. - Steve Jobs",
            "In the middle of every difficulty lies opportunity. - Albert Einstein",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "Success is not final, failure is not fatal: it is the courage to continue that counts. - Winston Churchill",
            "Happiness depends upon ourselves. - Aristotle"
    );

    public String getRandomQuote() {
        Random random = new Random();
        return quotes.get(random.nextInt(quotes.size()));
    }
}