package com.fernando.win.services;

import com.fernando.win.domain.User;
import com.fernando.win.dto.UserDto;
import com.fernando.win.dto.UserInsertDto;
import com.fernando.win.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserDto insert(UserInsertDto dto){
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = repository.save(entity);
        return new UserDto(entity);
    }

    public void copyDtoToEntity(UserDto dto, User entity){
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
    }

}
