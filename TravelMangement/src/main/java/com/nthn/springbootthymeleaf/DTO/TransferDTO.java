package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TransferDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer tourId;

    private Integer transportId;


    /**
     * Giờ khởi hành dự kiến
     */
    private LocalDateTime etDeparture;


    /**
     * Giờ đến dự kiến
     */
    private LocalDateTime etArrival;

}
