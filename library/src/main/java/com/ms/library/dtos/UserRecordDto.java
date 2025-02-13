package com.ms.library.dtos;

import com.ms.library.enums.RoleUser;
import com.ms.library.models.ClassModel;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

import java.util.Date;


public record UserRecordDto(RoleUser roleUser,
                            String registration,
                            String name,
                            @Email String email,
                            String password,
                            Date birthDate,
                            ClassModel classModel
) {
}
