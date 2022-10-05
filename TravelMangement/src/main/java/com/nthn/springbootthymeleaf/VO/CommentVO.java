package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class CommentVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    private Integer userId;

    @NotNull(message = "newsId can not null")
    private Integer newsId;

    @NotNull(message = "rating can not null")
    private Double rating;

    @NotNull(message = "content can not null")
    private String content;

}
