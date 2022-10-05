package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TourPriceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer tourId;

    private String groupPrice;

    private BigDecimal unitPrice;

}
