package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class DestinationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String provinceId;

    private String name;

    private String features;

    private String description;

    private String address;

    private String phone;

    private String email;

    private String website;

}
