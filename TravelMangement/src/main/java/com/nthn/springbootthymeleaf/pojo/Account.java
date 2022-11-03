package com.nthn.springbootthymeleaf.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "account",
        uniqueConstraints = {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
@NoArgsConstructor
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "permission_id")
    private Permission permission;

    @NotEmpty(message = "NotEmpty.account.userName")
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull(message = "NotEmpty.account.password")
    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String confirmPassword = getPassword();

    @NotEmpty(message = "NotEmpty.account.firstName")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "NotEmpty.account.lastName")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Pattern.account.email")
    @Column(name = "email")
    private String email;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime = LocalDateTime.now();

    @Column(name = "last_login", nullable = true)
    private LocalDateTime lastLogin;

    @Column(name = "active")
    private Boolean active = true;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "reset_token")
    private String resetToken;
    @Transient
    private MultipartFile multipartFile;

    @JsonIgnore
    @OneToMany(mappedBy = "account", orphanRemoval = true)
    private Set<Comment> comments = new LinkedHashSet<>();


    @Transient
    public String getPhotosImagePath() {
        if (avatarUrl == null || id == null) return null;

        return "/avatar/" + id + '/' + avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return id != null && Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
