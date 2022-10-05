package com.nthn.springbootthymeleaf.VO;


import lombok.Data;

import java.io.Serializable;

@Data
public class FeedbackQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer tourId;

    private Integer userId;

    private String description;

    private Double rating;

}
