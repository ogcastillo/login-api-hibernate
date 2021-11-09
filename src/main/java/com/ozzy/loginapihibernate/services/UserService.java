package com.ozzy.loginapihibernate.services;


import com.ozzy.loginapihibernate.dtos.UserAuthenticatedDto;
import com.ozzy.loginapihibernate.dtos.UserDto;
import com.ozzy.loginapihibernate.exceptions.DataNotCreatedException;
import com.ozzy.loginapihibernate.exceptions.DataNotFoundException;
import com.ozzy.loginapihibernate.exceptions.DataNotUpdatedException;
import com.ozzy.loginapihibernate.exceptions.DataNotValidException;
import com.ozzy.loginapihibernate.models.User;
import com.ozzy.loginapihibernate.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ozzy.loginapihibernate.services.UserRegistrationValidator.*;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserService {
    private final UserRepository userRepository;
    
    @Transactional
    public UserAuthenticatedDto saveUser(UserDto userDto ){
          Optional<User> userSaved = completeSaveOrUpdate(userDto);
          if (!userSaved.isPresent()){
            throw new DataNotCreatedException("DATA_COULD_NOT_BE_SAVED");
        }
    
        return new UserAuthenticatedDto(userSaved.get().getId(), userSaved.get().getUsername());
    }
    
    @Transactional(readOnly = true)
    public UserAuthenticatedDto getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new DataNotFoundException("USER_NOT_FOUND");
        }
        return user.map(value -> new UserAuthenticatedDto(value.getId(), value.getUsername())).orElse(null);
        
    }
    
    @Transactional(readOnly = true)
    public UserDto getUserAllData(Long id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new DataNotFoundException("USER_NOT_FOUND");
        }
        return user.map(value -> new UserDto(value.getFirstname(), value.getLastname(), value.getUsername(), value.getEmail(), value.getPassword())).orElse(null);
    }
    
    @Transactional
    public UserAuthenticatedDto updateUser(UserDto userDto){
        Optional<User> userSaved = completeSaveOrUpdate(userDto);
        
        if (!userSaved.isPresent()){
            throw new DataNotUpdatedException("USER_NOT_UPDATED");
        }
        
        
        return userSaved.map(value -> new UserAuthenticatedDto(value.getId(), value.getUsername())).orElse(null);
    }
    
    private Optional<User> completeSaveOrUpdate(UserDto userDto){
        //Complete user's data validations
        ValidationResult result = getUserDataValidation(userDto);
        if (result != ValidationResult.SUCCESS){
            throw new DataNotValidException(result.name());
        }
    
        User user = new User(userDto.getFirstname(), userDto.getLastname(), userDto.getUsername(), userDto.getEmail(), userDto.getPassword());
        return Optional.of(userRepository.save(user));
        
        
    }
    
    private ValidationResult getUserDataValidation(UserDto userDto) {
        return isFirstNameValid()
                .and(isLastNameValid())
                .and(isUsernameValid())
                .and(isEmailValid())
                .and(isPasswordComplexityValid())
                .apply(userDto);
    }
}
