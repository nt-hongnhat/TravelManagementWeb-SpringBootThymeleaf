package com.nthn.springbootthymeleaf.VO;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BookingQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer tourId;

    private Integer customerId;

    private Integer employeeId;

    private LocalDateTime bookingDate;

    private BigDecimal total;

}
