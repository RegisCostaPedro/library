package com.ms.library.controllers;

import com.ms.library.config.security.TokenService;
import com.ms.library.dtos.AuthenticationRecordDto;
import com.ms.library.dtos.LoginResponseDto;
import com.ms.library.dtos.UserRecordDto;
import com.ms.library.exceptions.EmailExistsException;
import com.ms.library.models.UserModel;
import com.ms.library.repositories.UserRepository;
import com.ms.library.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRecordDto dto){
    var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((UserModel) auth.getPrincipal());
    return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserModel> register(@RequestBody @Valid UserRecordDto userRecordDto) {

        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);

        return new ResponseEntity<>(userService.saveUser(userModel), HttpStatus.CREATED);
    }
}
