package com.nthn.springbootthymeleaf.VO;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class WorkForQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer agencyId;

    private Integer employeeId;

    private LocalDateTime hireDate;

}
