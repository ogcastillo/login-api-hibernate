 package com.ozzy.loginapihibernate.dtos;

 import lombok.AllArgsConstructor;
 import lombok.Getter;
 import lombok.NoArgsConstructor;
 import lombok.Setter;

 @NoArgsConstructor
 @AllArgsConstructor
 @Getter
 @Setter
public class UserAuthenticatedDto {

    private Long id;
    private String username;
    
}
