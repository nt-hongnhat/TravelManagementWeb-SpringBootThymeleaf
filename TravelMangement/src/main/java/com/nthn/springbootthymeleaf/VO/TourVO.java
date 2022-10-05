package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class TourVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "name can not null")
    private String name;

    private Integer categoryId;


    /**
     * Số ngày
     */
    @NotNull(message = "days can not null")
    private Integer days;


    /**
     * Số đêm
     */
    @NotNull(message = "night can not null")
    private Integer night;

    @NotNull(message = "slots can not null")
    private Integer slots;

    @NotNull(message = "departure can not null")
    private String departure;

    @NotNull(message = "destination can not null")
    private String destination;

}
