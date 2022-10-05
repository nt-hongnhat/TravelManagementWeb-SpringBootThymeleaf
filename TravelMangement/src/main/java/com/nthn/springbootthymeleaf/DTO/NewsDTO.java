package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class NewsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer userId;

    private String content;

    private LocalDateTime createDate;


    /**
     * Từ khoá để tìm kiếm tin
     */
    private String metaKeywords;

    private String metaTitle;

    private String metaDescription;

    private Boolean access;

}
