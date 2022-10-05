package com.nthn.springbootthymeleaf.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "news")
public class News implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    /**
     * Từ khoá để tìm kiếm tin
     */
    @Column(name = "meta_keywords", nullable = false)
    private String metaKeywords;

    @Column(name = "meta_title", nullable = false)
    private String metaTitle;

    @Column(name = "meta_description", nullable = false)
    private String metaDescription;

    @Column(name = "access", nullable = false)
    private Boolean access = Boolean.FALSE;

    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        News news = (News) o;
        return id != null && Objects.equals(id, news.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
