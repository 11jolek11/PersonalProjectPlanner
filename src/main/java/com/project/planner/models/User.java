package com.project.planner.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "_user")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "userIdGen", sequenceName = "userIdSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGen")
    private Long id;
    @Email
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Set<Project> projects;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
