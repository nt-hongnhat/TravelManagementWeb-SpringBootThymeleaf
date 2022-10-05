package com.nthn.springbootthymeleaf.VO;


import lombok.Data;

import java.io.Serializable;

@Data
public class AgencyQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String address;

    private String phone;

}
