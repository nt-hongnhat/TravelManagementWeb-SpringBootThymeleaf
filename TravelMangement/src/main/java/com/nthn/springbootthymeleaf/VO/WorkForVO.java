package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class WorkForVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "agencyId can not null")
    private Integer agencyId;

    @NotNull(message = "employeeId can not null")
    private Integer employeeId;

    @NotNull(message = "hireDate can not null")
    private LocalDateTime hireDate;

}
