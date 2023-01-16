package com.fernando.win.services;

import com.fernando.win.domain.User;
import com.fernando.win.dto.UserDto;
import com.fernando.win.dto.UserInsertDto;
import com.fernando.win.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

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

    @Transactional(readOnly = true)
    public Page<UserDto> findAllPageable(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);
        return users.map(UserDto::new);
    }

    public UserDto findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id " + id + " não encontrado."));
        return new UserDto(entity);
    }

    public void copyDtoToEntity(UserDto dto, User entity){
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        if(user == null){
            logger.error("Usuário não encontrado: " + username);
            throw new UsernameNotFoundException("E-mail não encontrado.");
        }
        logger.info("Usuário encontrado: " + username);
        return user;
    }



}
