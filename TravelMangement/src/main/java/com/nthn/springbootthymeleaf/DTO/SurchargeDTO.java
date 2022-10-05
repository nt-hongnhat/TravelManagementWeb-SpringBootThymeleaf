package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SurchargeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

    private Double surcharge;

    private LocalDateTime createDate;


    /**
     * Hạn sử dụng
     */
    private LocalDateTime expiryDate;

}
