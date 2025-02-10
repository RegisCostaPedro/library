package com.ms.library.repositories;

import com.ms.library.models.ClassModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassRepository extends JpaRepository<ClassModel,UUID> {
}
