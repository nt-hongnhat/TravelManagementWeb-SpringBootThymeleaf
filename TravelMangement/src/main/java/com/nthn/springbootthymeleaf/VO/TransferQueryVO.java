package com.nthn.springbootthymeleaf.VO;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TransferQueryVO implements Serializable {
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
