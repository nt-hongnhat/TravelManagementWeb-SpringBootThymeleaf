package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "username can not null")
    private String username;

    @NotNull(message = "password can not null")
    private String password;

    private String email;

    @NotNull(message = "createTime can not null")
    private LocalDateTime createTime;

    private String avatarUrl;

    private Integer userRoleId;

}
