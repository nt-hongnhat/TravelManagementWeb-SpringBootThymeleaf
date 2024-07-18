package com.nthn.springbootthymeleaf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "account", uniqueConstraints = {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
@NoArgsConstructor
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY)
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
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Email(message = "Pattern.account.email")
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime = LocalDateTime.now();
    
    @Column(name = "last_login")
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
    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Comment> comments = new LinkedHashSet<>();
    
    
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Customer customer;
    
    
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
    
    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", permission=" + permission + ", username='" + username + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + '}';
    }
}
