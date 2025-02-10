package com.ms.library.models;


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
    private RoleUser roleUser;
    private String registration;
    private String name;
    private String email;
    private String password;
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassModel classModel;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<ReservationModel> reservations = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<LoanModel> loans = new HashSet<>();;


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

    public UUID getId_user() {
        return userId;
    }

    public void setId_user(UUID userId) {
        this.userId = userId;
    }

    public RoleUser getRole_user() {
        return roleUser;
    }

    public void setRole_user(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirth_date() {
        return birthDate;
    }

    public void setBirth_date(Date birthDate) {
        this.birthDate = birthDate;
    }

    public ClassModel getClass_id() {
        return classModel;
    }

    public void setClass_id(ClassModel classModel) {
        this.classModel = classModel;
    }

    public Set<ReservationModel> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationModel> reservations) {
        this.reservations = reservations;
    }

    public Set<LoanModel> getLoans() {
        return loans;
    }

    public void setLoans(Set<LoanModel> loans) {
        this.loans = loans;
    }
}
