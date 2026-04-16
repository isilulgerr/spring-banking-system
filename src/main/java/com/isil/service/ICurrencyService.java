package com.isil.service;

import java.math.BigDecimal;

public interface ICurrencyService {
    BigDecimal getExchangeRate(String from, String to);
}
