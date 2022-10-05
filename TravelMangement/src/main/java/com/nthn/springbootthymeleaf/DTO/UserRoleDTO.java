package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

    private Integer level;

}
