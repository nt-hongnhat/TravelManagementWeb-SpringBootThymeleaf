package com.nthn.springbootthymeleaf.VO;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BookingDetailQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private Integer bookingId;

    private Integer surchargeId;

    private Integer quantity;

    private Double discount;

    private BigDecimal unitPrice;

}
