package com.nthn.springbootthymeleaf.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
public class SearchTourDTO {
    private String departurePlace="";
    private String destinationPlace="";
    private String rangeDate="";
    private String rangePrice="";
    private BigDecimal fromPrice;
    private BigDecimal toPrice;
    private LocalDate fromDepartureDate;
    private LocalDate toDepartureDate;
    private Integer numberPeople = 1;

    public void convertPrice() {
        String[] rangePrices;
        List<BigDecimal> prices = new ArrayList<>();

        rangePrices = rangePrice.split("-");
        for (String price : rangePrices) {
            prices.add(BigDecimal.valueOf(Long.parseLong(price)));
        }

        this.fromPrice = prices.stream().min(BigDecimal::compareTo).orElse(BigDecimal.valueOf(0));
        this.toPrice = prices.stream().max(BigDecimal::compareTo).orElse(BigDecimal.valueOf(0));
    }

    public void convertDate() {
        String[] date;
        date = rangeDate.split("-");
        System.out.println(date[0].trim());
        System.out.println(date[1].trim());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.fromDepartureDate = LocalDate.parse(date[0].trim(), formatter);
        this.toDepartureDate = LocalDate.parse(date[1].trim(), formatter);
    }
}
