package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class DistrictDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String name;

    private String provinceId;

}
