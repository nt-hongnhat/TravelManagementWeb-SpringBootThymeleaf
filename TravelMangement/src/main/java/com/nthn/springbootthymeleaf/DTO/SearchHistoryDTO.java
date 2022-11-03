package com.nthn.springbootthymeleaf.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Setter
@Getter
public class SearchHistoryDTO {
    private String rangeDate;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String statusPayment;
    private boolean payment;

    public void convertDate(String rangeDate) {
        String[] date;
        date = rangeDate.split("-");
        System.out.println(date[0].trim());
        System.out.println(date[1].trim());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.fromDate = LocalDate.parse(date[0].trim(), formatter);
        this.toDate = LocalDate.parse(date[1].trim(), formatter);
    }

    public void convertPayment(String statusPayment) {
        this.payment = Boolean.getBoolean(statusPayment);
    }

}
