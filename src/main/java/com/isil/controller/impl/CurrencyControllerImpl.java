package com.isil.controller.impl;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.isil.controller.ICurrencyController;
import com.isil.service.ICurrencyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@RestController
@RequestMapping("/api/currency")
public class CurrencyControllerImpl implements ICurrencyController {

    @Autowired
    private ICurrencyService currencyService;

    @Operation(summary = "Get exchange rate", description = "Returns the exchange rate between two currencies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved exchange rate", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"rate\": 1.123}"))),
            @ApiResponse(responseCode = "404", description = "Exchange rate not found", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\": \"Exchange rate not found\"}")))
    })
    @Override
    @GetMapping("/rate")
    public ResponseEntity<BigDecimal> getExchangeRate(
            @RequestParam String from,
            @RequestParam String to) {
        BigDecimal rate = currencyService.getExchangeRate(from, to);
        return ResponseEntity.ok(rate);
    }
}
