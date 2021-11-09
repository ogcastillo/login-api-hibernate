package com.ozzy.loginapihibernate.controllers;


import com.ozzy.loginapihibernate.dtos.UserAuthenticatedDto;
import com.ozzy.loginapihibernate.dtos.UserDto;
import com.ozzy.loginapihibernate.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
@AllArgsConstructor(onConstructor_= @Autowired)
public class UserController {
    private final UserService userService;
    
    @PostMapping(value="/save", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserAuthenticatedDto saveUser(@RequestBody UserDto newUser, HttpServletRequest req, HttpServletResponse res){
        return userService.saveUser(newUser);
    }
    
    @GetMapping(value="/get", produces=APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserAuthenticatedDto getUser(@RequestParam Long userId,HttpServletRequest req, HttpServletResponse res){
        return userService.getUser(userId);
    }
    
   
    @GetMapping(value = "/getAllData", produces=APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserData(@RequestParam Long userId, HttpServletRequest req,HttpServletResponse res){
        return userService.getUserAllData(userId);
    }
    
    
    @PostMapping(value = "/update", consumes = APPLICATION_JSON_VALUE, produces=APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserAuthenticatedDto updateUser(@RequestBody UserDto userToBeUpdated, HttpServletRequest req, HttpServletResponse res){
        return userService.updateUser(userToBeUpdated);
    }
    
}
