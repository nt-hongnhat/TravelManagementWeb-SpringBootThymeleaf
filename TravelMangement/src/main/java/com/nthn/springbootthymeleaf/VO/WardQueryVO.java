package com.nthn.springbootthymeleaf.VO;


import lombok.Data;

import java.io.Serializable;

@Data
public class WardQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String districtId;

    private String provinceId;

}
