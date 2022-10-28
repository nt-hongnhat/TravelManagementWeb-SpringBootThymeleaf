package com.nthn.springbootthymeleaf.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
public class SearchTourDTO {
    private String departurePlace;
    private String destinationPlace;
    private String rangeDate;
    private String rangePrice;
    private Integer fromPrice;
    private Integer toPrice;
    private LocalDate fromDepartureDate;
    private LocalDate toDepartureDate;
    private Integer numberPeople = 1;

    public void convertPrice(String rangePrice) {
        String[] rangePrices;
        List<Integer> prices = new ArrayList<>();

        rangePrices = rangePrice.split("-");
        for (String price : rangePrices) {
            prices.add(Integer.valueOf(price));
        }

        this.fromPrice = prices.stream().mapToInt(value -> value).min().orElseThrow();
        this.toPrice = prices.stream().mapToInt(value -> value).max().orElseThrow();
    }

    public void convertDate(String rangeDate) {
        String[] date;
        date = rangeDate.split("-");
        System.out.println(date[0].trim());
        System.out.println(date[1].trim());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.fromDepartureDate = LocalDate.parse(date[0].trim(), formatter);
        this.toDepartureDate = LocalDate.parse(date[1].trim(), formatter);
    }
}
