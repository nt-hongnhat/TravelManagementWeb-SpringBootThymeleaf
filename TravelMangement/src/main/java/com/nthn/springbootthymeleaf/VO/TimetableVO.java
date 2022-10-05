package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;


@Data
public class TimetableVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    private Integer tourId;

    @NotNull(message = "destinationId can not null")
    private Integer destinationId;

    @NotNull(message = "ordinalDate can not null")
    private Integer ordinalDate;

    @NotNull(message = "timer can not null")
    private LocalTime timer;

    @NotNull(message = "activities can not null")
    private String activities;

}
