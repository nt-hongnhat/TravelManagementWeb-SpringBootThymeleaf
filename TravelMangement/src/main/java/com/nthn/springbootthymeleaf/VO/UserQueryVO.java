package com.nthn.springbootthymeleaf.VO;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String password;

    private String email;

    private LocalDateTime createTime;

    private String avatarUrl;

    private Integer userRoleId;

}
