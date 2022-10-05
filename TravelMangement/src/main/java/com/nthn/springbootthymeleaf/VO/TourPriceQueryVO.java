package com.nthn.springbootthymeleaf.VO;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TourPriceQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer tourId;

    private String groupPrice;

    private BigDecimal unitPrice;

}
