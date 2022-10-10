package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EmployeeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer accountId;

    private String firstName;

    private String lastName;

    private String identifiedcard;

    private String gender;

    private LocalDateTime birthDate;

    private String address;

    private String phone;

}
