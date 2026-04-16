package com.isil.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.isil.service.ICurrencyService;

@Service
public class CurrencyServiceImpl implements ICurrencyService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${exchange.api.key:}")
    private String apiKey;

    @Override
    public BigDecimal getExchangeRate(String from, String to) {
        String url;
        String baseCurrency = from.trim().toUpperCase(java.util.Locale.ENGLISH);
        String targetCurrency = to.trim().toUpperCase(java.util.Locale.ENGLISH);
        
        // Eğer API Key tanımlandıysa (V6) ya da tanımlanmadıysa (V4 Ücretsiz) çalışır
        if (apiKey != null && !apiKey.isEmpty() && !apiKey.equals("BURAYA_API_KEY_YAZIN")) {
            url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;
        } else {
            url = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;
        }
        
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            
            if (response != null) {
                // V6 versiyonu "conversion_rates", V4 versiyonu "rates" kullanır
                @SuppressWarnings("unchecked")
                Map<String, Object> rates = response.containsKey("conversion_rates") ? 
                        (Map<String, Object>) response.get("conversion_rates") : 
                        (Map<String, Object>) response.get("rates");
                        
                if (rates != null && rates.containsKey(targetCurrency)) {
                    Object rateObj = rates.get(targetCurrency);
                    return new BigDecimal(rateObj.toString());
                } else {
                    throw new RuntimeException("Hedef para birimi bulunamadı (Target currency not found): '" + targetCurrency + "'. (Boşluk bırakmadığından emin ol!)");
                }
            }
            throw new RuntimeException("Döviz kurları alınamadı, API yanıtı boş.");
            
        } catch (Exception e) {
            throw new RuntimeException("Kur hesaplanırken bir hata oluştu: " + e.getMessage());
        }
    }
}
