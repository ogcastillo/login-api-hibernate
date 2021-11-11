package com.ozzy.loginapihibernate.repositories;

import com.ozzy.loginapihibernate.dtos.UserAuthenticatedDto;
import com.ozzy.loginapihibernate.dtos.UserDto;
import com.ozzy.loginapihibernate.models.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor(onConstructor_=@Autowired)
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private EntityManager em;
    
    @Autowired
    private UserRepository sut;
    
    @Test
    @Rollback
    @DisplayName("integration_test_saveUser")
    public void test_saveUser(){
        //Arrange
        Long exptectedId = 1L;
        String expectedUsername;
        UserDto userDto = new UserDto("Jane", "Doe", "jane.doe", "jane.doe@gmail.com", "P@ssw0rd");
        
        User user= new User(exptectedId, userDto.getFirstname(), userDto.getLastname(), userDto.getUsername(), userDto.getEmail(), userDto.getPassword());
    
        UserAuthenticatedDto userAuthenticatedDto;
        
        expectedUsername = userDto.getUsername();
        
        //Act
        sut.saveAndFlush(user);
        em.clear();
        Optional<User> currentUser = sut.findById(exptectedId);
        
        //Assert
        assertEquals(user.getUsername(), currentUser.get().getUsername());
        
    }
}