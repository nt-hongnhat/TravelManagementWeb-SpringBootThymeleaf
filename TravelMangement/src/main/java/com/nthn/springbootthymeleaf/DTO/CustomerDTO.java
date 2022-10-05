package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer userId;

    private String fullname;

    private String gender;

    private String identifiedcard;

    private String nationality;

    private String phone;

    private String address;

}
