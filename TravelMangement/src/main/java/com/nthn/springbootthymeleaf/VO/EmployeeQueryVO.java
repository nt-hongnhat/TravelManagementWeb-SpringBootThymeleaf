package com.nthn.springbootthymeleaf.VO;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EmployeeQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;

    private String firstName;

    private String lastName;

    private String identifiedcard;

    private String gender;

    private LocalDateTime birthDate;

    private String address;

    private String phone;

}
