package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class DistrictVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private String id;

    @NotNull(message = "name can not null")
    private String name;

    @NotNull(message = "provinceId can not null")
    private String provinceId;

}
