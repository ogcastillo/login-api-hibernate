package com.ozzy.loginapihibernate.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    
}
