package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class BookingDetailVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private String id;

    @NotNull(message = "bookingId can not null")
    private Integer bookingId;

    private Integer surchargeId;

    @NotNull(message = "quantity can not null")
    private Integer quantity;

    @NotNull(message = "discount can not null")
    private Double discount;

    @NotNull(message = "unitPrice can not null")
    private BigDecimal unitPrice;

}
