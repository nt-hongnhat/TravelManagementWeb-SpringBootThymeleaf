package com.nthn.springbootthymeleaf.VO;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Data
public class TimetableQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer tourId;

    private Integer destinationId;

    private Integer ordinalDate;

    private LocalTime timer;

    private String activities;

}
