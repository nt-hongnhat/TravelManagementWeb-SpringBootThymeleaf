package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AccountDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String password;
    private String confirm;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createTime;
    private String avatarUrl;
    private Integer permissionId;

}
