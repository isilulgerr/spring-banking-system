package com.isil.controller;

import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;

public interface ICurrencyController {
    ResponseEntity<BigDecimal> getExchangeRate(String from, String to);
}
