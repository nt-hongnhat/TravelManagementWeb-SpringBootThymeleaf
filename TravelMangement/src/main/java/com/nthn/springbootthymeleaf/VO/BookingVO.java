package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class BookingVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    private Integer tourId;

    private Integer customerId;

    private Integer employeeId;

    @NotNull(message = "bookingDate can not null")
    private LocalDateTime bookingDate;

    @NotNull(message = "total can not null")
    private BigDecimal total;

}
