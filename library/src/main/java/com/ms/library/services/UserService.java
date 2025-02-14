package com.ms.library.services;

import com.ms.library.models.ClassModel;
import com.ms.library.models.UserModel;
import com.ms.library.repositories.ClassRepository;
import com.ms.library.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassRepository classRepository;

    public UserModel saveUser(UserModel model) {

        ClassModel classModel = classRepository.findById(model.getClassModel().getClassId()).get();

        model.setClassModel(classModel);

        return userRepository.save(model);
    }

    public List<UserModel> findAllUsers() {
        return userRepository.findAll();
    }

    public UserModel findUserById(UUID id) {
        return userRepository.findById(id).get();
    }

    public UserModel updateUser(UserModel userModel, UUID id) {
        UserModel userFind = userRepository.findById(id).get();
        ClassModel classModel = classRepository.findById(userModel.getClassModel().getClassId()).get();
        userFind.setEmail(userModel.getEmail());
        userFind.setName(userModel.getName());
        userFind.setRoleUser(userModel.getRoleUser());
        userFind.setBirthDate(userModel.getBirthDate());
        userFind.setPassword(userModel.getPassword());
        userFind.setClassModel(classModel);
        return userRepository.save(userFind);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);

    }


}
