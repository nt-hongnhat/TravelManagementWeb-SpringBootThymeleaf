package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class AgencyVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "name can not null")
    private String name;

    @NotNull(message = "address can not null")
    private String address;

    @NotNull(message = "phone can not null")
    private String phone;

}
