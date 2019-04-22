package com.example.demo.model.user;

import com.example.demo.model.AbstractBaseEntity;
import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "user_unique_email_idx")})
public class User extends AbstractBaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @Column(name = "firstName", nullable = false)
    @NotBlank
    private String firstName;

    @Column(name = "lastName")
    private String lastName;


    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5)
    // https://stackoverflow.com/a/12505165/548473
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date registered = new Date();

    public User(@Email @NotBlank String email, @NotBlank String firstName, String lastName, @NotBlank @Size(min = 5) String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(Long id, @Email @NotBlank String email, @NotBlank String firstName, @NotBlank @Size(min = 5) String password, boolean enabled, Set<Role> roles) {
        super(id);
        this.email = email;
        this.firstName = firstName;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public User(@Email @NotBlank String email, @NotBlank String firstName, @NotBlank @Size(min = 5) String password) {
        this.email = email;
        this.firstName = firstName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User(@Email @NotBlank String email, @NotBlank String firstName, String lastName, @NotBlank @Size(min = 5) String password, boolean enabled, @NotNull Date registered, Set<Role> roles) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.roles = roles;
    }


    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 200)
    private Set<Role> roles;

;

    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRegistered(), u.getRoles());
    }

    public User(Long id, String email, String password, Role role, Role... roles) {
        this(id, email, password, true, new Date(), EnumSet.of(role, roles));
    }


    public User(Long id, String email, String password, boolean enabled, Date registered, Collection<Role> roles) {
        super(id);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }


    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", email=" + email +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}