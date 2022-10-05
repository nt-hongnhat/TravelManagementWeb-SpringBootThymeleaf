package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class SurchargeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "name can not null")
    private String name;

    @NotNull(message = "surcharge can not null")
    private Double surcharge;

    @NotNull(message = "createDate can not null")
    private LocalDateTime createDate;


    /**
     * Hạn sử dụng
     */
    @NotNull(message = "expiryDate can not null")
    private LocalDateTime expiryDate;

}
