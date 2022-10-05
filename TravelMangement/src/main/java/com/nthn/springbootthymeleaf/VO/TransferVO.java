package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class TransferVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    private Integer tourId;

    private Integer transportId;


    /**
     * Giờ khởi hành dự kiến
     */
    @NotNull(message = "etDeparture can not null")
    private LocalDateTime etDeparture;


    /**
     * Giờ đến dự kiến
     */
    @NotNull(message = "etArrival can not null")
    private LocalDateTime etArrival;

}
