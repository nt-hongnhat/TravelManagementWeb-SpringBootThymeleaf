package com.nthn.springbootthymeleaf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
   
    
    private double amount;
    
    private String currency;
    
    private String description;
    
    private String intent;
    
    private String method;
    
    private BigDecimal total;
    
    public Double calculateTotal() {
        return total.divide(BigDecimal.valueOf(25345), 2, RoundingMode.HALF_UP).doubleValue();
    }
    
    @Override
    public String toString() {
        return "PaymentDTO{" + "total=" + total + ", currency='" + currency + '\'' + ", method='" + method + '\'' + ", intent='" + intent + '\'' + ", description='" + description + '\'' + '}';
    }
}
