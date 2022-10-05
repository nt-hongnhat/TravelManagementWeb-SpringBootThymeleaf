package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class WardVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private String id;

    @NotNull(message = "name can not null")
    private String name;

    @NotNull(message = "districtId can not null")
    private String districtId;

    @NotNull(message = "provinceId can not null")
    private String provinceId;

}
