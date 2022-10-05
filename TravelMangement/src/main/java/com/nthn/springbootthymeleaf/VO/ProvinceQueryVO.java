package com.nthn.springbootthymeleaf.VO;


import lombok.Data;

import java.io.Serializable;

@Data
public class ProvinceQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

}
