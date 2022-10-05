package com.nthn.springbootthymeleaf.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class FeedbackDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Integer tourId;

    private Integer userId;

    private String description;

    private Double rating;

}
