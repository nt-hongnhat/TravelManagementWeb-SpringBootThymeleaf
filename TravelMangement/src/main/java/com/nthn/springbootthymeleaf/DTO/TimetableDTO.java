package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Data
public class TimetableDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer tourId;

    private Integer destinationId;

    private Integer ordinalDate;

    private LocalTime timer;

    private String activities;

}
