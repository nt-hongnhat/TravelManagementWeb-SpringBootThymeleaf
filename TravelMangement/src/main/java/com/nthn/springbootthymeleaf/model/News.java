package com.nthn.springbootthymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "news")
public class News {
    @Id
    @Column(name = "id", length = -1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "title")
    private String title;

    @Column(name = "create_time")
    private LocalDateTime createTime = LocalDateTime.now();

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "images")
    private String images;

    @Column(name = "access")
    private Boolean access;

    @Column(name = "content", length = 255)
    private String content;

    @Column(name = "meta_description", length = 255)
    private String metaDescription;

    @Column(name = "meta_keywords", length = 255)
    private String metaKeywords;

    @Column(name = "meta_title", length = 255)
    private String metaTitle;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "views", length = -1)
    private Integer views;


}
