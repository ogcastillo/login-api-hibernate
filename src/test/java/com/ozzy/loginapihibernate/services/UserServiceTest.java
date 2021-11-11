package com.ozzy.loginapihibernate.services;


import com.ozzy.loginapihibernate.dtos.UserAuthenticatedDto;
import com.ozzy.loginapihibernate.dtos.UserDto;
import com.ozzy.loginapihibernate.exceptions.DataNotValidException;
import com.ozzy.loginapihibernate.models.User;
import com.ozzy.loginapihibernate.repositories.UserRepository;
import com.ozzy.loginapihibernate.services.UserService;
import com.ozzy.loginapihibernate.services.ValidationResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;


class UserServiceTest {
    
    @Mock
    private UserRepository mockUserRepository;
    
    @InjectMocks
    private UserService sut;
    
    
    @BeforeEach
    void setUp() {
        openMocks(this);
    }
    
    @AfterEach
    void tearDown() {
        mockUserRepository = null;
    }
    
    @Test
    void test_saveUserWithValidUserData() {
        //Arrange
        Long expectedId=1L;
        String expectedUsername;
        
        UserDto userDto = new UserDto("Ozzy",
                                      "Castillo",
                                      "ozzy.castillo",
                                      "ozzy.castillo@gmail.com", "P@ssw0rd");
        
        User user = new User(expectedId, userDto.getFirstname(), userDto.getLastname(), userDto.getUsername()
                            ,userDto.getEmail(),userDto.getPassword());
        
        when(mockUserRepository.save(any(User.class))).thenReturn(user);
        
        expectedUsername = userDto.getUsername();
        
        //Act
        UserAuthenticatedDto actualResult = sut.saveUser(userDto);
        
        //Assert
        assertEquals(expectedUsername,actualResult.getUsername());
        verify(mockUserRepository, times(1)).save(any(User.class));
    }
    
    @Test
    public void test_saveUserWithWrongFirstname(){
        //Arrange
        UserDto userDto = new UserDto("12345",
                                      "Castillo",
                                      "ozzy.castillo",
                                      "ozzy.castillo@gmail.com","P@ssw0rd");
        
        ValidationResult expectedValidationResult = ValidationResult.FIRSTNAME_INVALID;
        
        //Act
        
        //Assert
        Throwable throwable = assertThrows(DataNotValidException.class, ()->{
            sut.saveUser(userDto);
        });
        
        assertEquals(String.valueOf(expectedValidationResult), throwable.getMessage());
    }
}