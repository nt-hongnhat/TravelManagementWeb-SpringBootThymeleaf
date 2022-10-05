package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class CustomerVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    private Integer userId;

    @NotNull(message = "fullname can not null")
    private String fullname;

    @NotNull(message = "gender can not null")
    private String gender;

    @NotNull(message = "identifiedcard can not null")
    private String identifiedcard;

    private String nationality;

    private String phone;

    private String address;

}
