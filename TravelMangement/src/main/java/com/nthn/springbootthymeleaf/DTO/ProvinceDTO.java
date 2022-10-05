package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProvinceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String name;

}
