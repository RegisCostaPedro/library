package com.ms.library.services;

import com.ms.library.models.ClassModel;
import com.ms.library.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClassService {

    @Autowired
    ClassRepository classRepository;


    public ClassModel saveClass(ClassModel classModel){
       return classRepository.save(classModel);
    }

    public List<ClassModel> findAll(){
        return classRepository.findAll();

    }
    public ClassModel findClassById(UUID uuid){
        return classRepository.findById(uuid).get();

    }

    public Object updateClass(ClassModel classModel,UUID uuid){
        ClassModel classFind = findClassById(uuid);

        if(classFind.getClassId() == null){
            return "User not found";
        }
        classFind.setNumClass(classModel.getNumClass());
        classFind.setCourseName(classModel.getCourseName());
        return classRepository.save(classFind);


    }
    public void deleteClass(UUID uuid){
        ClassModel classFind = findClassById(uuid);
         classRepository.deleteById(classFind.getClassId());

    }

}
