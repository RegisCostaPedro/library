package com.ms.library.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ms.library.enums.RoleUser;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    @Enumerated(EnumType.ORDINAL)
    private RoleUser roleUser;
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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<ReservationModel> reservations = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
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

    public String getPassword() {
        return password;
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
