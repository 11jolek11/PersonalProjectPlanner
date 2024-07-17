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
//    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Set<Project> projects;
    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Set<Task> userTasks;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Task> getUserTasks() {
        return userTasks;
    }

    public void setUserTasks(Set<Task> userTasks) {
        this.userTasks = userTasks;
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
