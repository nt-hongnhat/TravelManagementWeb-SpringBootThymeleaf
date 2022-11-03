package com.nthn.springbootthymeleaf.pojo;

import lombok.Data;

@Data
public class Google {
    private String id;
    private String email;
    private boolean verified_email;
    private String name;
    private String given_name;
    private String family_name;
    private String link;
    private String picture;
}
