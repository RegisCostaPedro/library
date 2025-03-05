package com.ms.library.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.library.enums.RoleUser;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "TB_USER")
public class UserModel implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Enumerated(EnumType.ORDINAL)
    private RoleUser roleUser;

    @Column(unique = true)
    private String registration;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassModel classModel;

    @OneToMany(mappedBy = "userModel", fetch = FetchType.EAGER)
    private Set<ReservationModel> reservations = new HashSet<>();

    @OneToMany(mappedBy = "userModel", fetch = FetchType.EAGER)
    private Set<LoanModel> loans = new HashSet<>();


    public UserModel() {
    }

    public UserModel(UUID userId, RoleUser roleUser, String registration, String name, String email, String password, Date birthDate, ClassModel classModel, Set<ReservationModel> reservations, Set<LoanModel> loans) {
        this.userId = userId;
        this.roleUser = roleUser;
        this.registration = registration;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.classModel = classModel;
        this.reservations = reservations;
        this.loans = loans;
    }


    public ClassModel getClassModel() {
        return classModel;
    }

    public void setClassModel(ClassModel classModel) {
        this.classModel = classModel;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.roleUser == RoleUser.ADMIN)return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
        // se começar a dar pau na verificação coloca pra retornar FALSE
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }



}
