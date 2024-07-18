package com.nthn.springbootthymeleaf.dto;

import lombok.Data;

@Data
public class BookingTourDTO {
    private String address;

    private String fullName;
    private String identified;

    private Integer numberAdult = 1;

    private Integer numberChildren = 0;

    private Integer numberInfants = 0;

    private Integer numberYoungChildren = 0;

    private String phone;

    private int tourId;
}
