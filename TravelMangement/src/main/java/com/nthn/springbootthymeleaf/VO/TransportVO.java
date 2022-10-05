package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class TransportVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "name can not null")
    private String name;

    @NotNull(message = "status can not null")
    private Boolean status;

    @NotNull(message = "capacity can not null")
    private Integer capacity;

}
