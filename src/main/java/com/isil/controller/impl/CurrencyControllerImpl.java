package com.isil.controller.impl;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.isil.controller.ICurrencyController;
import com.isil.service.ICurrencyService;

@RestController
@RequestMapping("/api/currency")
public class CurrencyControllerImpl implements ICurrencyController {

    @Autowired
    private ICurrencyService currencyService;

    @Override
    @GetMapping("/rate")
    public ResponseEntity<BigDecimal> getExchangeRate(
            @RequestParam String from,
            @RequestParam String to) {
        BigDecimal rate = currencyService.getExchangeRate(from, to);
        return ResponseEntity.ok(rate);
    }
}
