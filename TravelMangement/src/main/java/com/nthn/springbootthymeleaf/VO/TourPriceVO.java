package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class TourPriceVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    private Integer tourId;

    @NotNull(message = "groupPrice can not null")
    private String groupPrice;

    @NotNull(message = "unitPrice can not null")
    private BigDecimal unitPrice;

}
