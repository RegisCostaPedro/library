package com.ms.library.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_CLASS")
public class ClassModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID classId;
    private Integer numClass;
    private String courseName;

    @OneToMany(mappedBy = "classModel")
    private Set<UserModel> users = new HashSet<>();




    public UUID getClassId() {
        return classId;
    }

    public void setClassId(UUID classId) {
        this.classId = classId;
    }

    public Integer getNumClass() {
        return numClass;
    }

    public void setNumClass(Integer numClass) {
        this.numClass = numClass;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }




}
