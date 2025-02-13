package com.ms.library.controllers;

import com.ms.library.dtos.ClassRecordDto;
import com.ms.library.dtos.UserRecordDto;
import com.ms.library.models.ClassModel;
import com.ms.library.models.UserModel;
import com.ms.library.services.UserService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LogManager.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody @Valid UserRecordDto userRecordDto){
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto,userModel);
        log.info(userModel);
        return  new ResponseEntity<>(userService.saveUser(userModel), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<UserModel>> findAllClasses(){
        return  new ResponseEntity<>(userService.findAllUsers(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findClassById(@PathVariable UUID id){
        return  new ResponseEntity<>(userService.findUserById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody @Valid  UserRecordDto userRecordDto,@PathVariable UUID id){
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto,userModel);
        return  new ResponseEntity<>(userService.updateUser(userModel,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable UUID id){
        userService.deleteUser(id);
    }


}
