package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class EmployeeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    private Integer userId;

    @NotNull(message = "firstName can not null")
    private String firstName;

    @NotNull(message = "lastName can not null")
    private String lastName;

    @NotNull(message = "identifiedcard can not null")
    private String identifiedcard;

    @NotNull(message = "gender can not null")
    private String gender;

    @NotNull(message = "birthDate can not null")
    private LocalDateTime birthDate;

    private String address;

    private String phone;

}
