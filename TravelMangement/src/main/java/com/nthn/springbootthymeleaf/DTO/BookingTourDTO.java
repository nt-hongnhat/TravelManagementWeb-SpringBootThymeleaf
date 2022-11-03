package com.nthn.springbootthymeleaf.DTO;

import lombok.Data;

@Data
public class BookingTourDTO {
    private int tourId;
    private String fullName;
    private String identified;
    private String phone;
    private String address;
    private Integer numberAdult = 1;
    private Integer numberChildren = 0;
    private Integer numberYoungChildren = 0;
    private Integer numberInfants = 0;
}
