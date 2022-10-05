package com.nthn.springbootthymeleaf.VO;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SurchargeQueryVO implements Serializable {
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
