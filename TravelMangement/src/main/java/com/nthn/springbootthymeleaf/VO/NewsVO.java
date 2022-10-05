package com.nthn.springbootthymeleaf.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class NewsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id can not null")
    private Integer id;

    private Integer userId;

    @NotNull(message = "content can not null")
    private String content;

    @NotNull(message = "createDate can not null")
    private LocalDateTime createDate;


    /**
     * Từ khoá để tìm kiếm tin
     */
    @NotNull(message = "metaKeywords can not null")
    private String metaKeywords;

    @NotNull(message = "metaTitle can not null")
    private String metaTitle;

    @NotNull(message = "metaDescription can not null")
    private String metaDescription;

    @NotNull(message = "access can not null")
    private Boolean access;

}
