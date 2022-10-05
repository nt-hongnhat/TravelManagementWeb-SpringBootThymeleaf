package com.nthn.springbootthymeleaf.VO;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer level;

}
