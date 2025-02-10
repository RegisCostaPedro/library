package com.ms.library.controllers;

import com.ms.library.dtos.ClassRecordDto;
import com.ms.library.models.ClassModel;
import com.ms.library.services.ClassService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<ClassModel> createClass(@RequestBody @Valid  ClassRecordDto classRecordDto){
        var classModel = new ClassModel();
        BeanUtils.copyProperties(classRecordDto,classModel);
        return  new ResponseEntity<>(classService.saveClass(classModel),HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ClassModel>> findAllClasses(){
        return  new ResponseEntity<>(classService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClassModel> findClassById(@PathVariable UUID id){
        return  new ResponseEntity<>(classService.findClassById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClass(@RequestBody @Valid  ClassRecordDto classRecordDto,@PathVariable UUID id){
        var classModel = new ClassModel();
        BeanUtils.copyProperties(classRecordDto,classModel);
        return  new ResponseEntity<>(classService.updateClass(classModel,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteClassById(@PathVariable UUID id){
          classService.deleteClass(id);
    }



}
