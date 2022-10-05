package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class FeedbackVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    @NotNull(message = "tourId can not null")
    private Integer tourId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String description;

    @NotNull(message = "rating can not null")
    private Double rating;

}
