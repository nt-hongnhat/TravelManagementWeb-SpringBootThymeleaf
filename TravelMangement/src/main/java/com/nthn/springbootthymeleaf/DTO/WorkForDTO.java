package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class WorkForDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer agencyId;

    private Integer employeeId;

    private LocalDateTime hireDate;

}
