package com.fernando.win.services;

import com.fernando.win.domain.Role;
import com.fernando.win.domain.User;
import com.fernando.win.dto.RoleDto;
import com.fernando.win.dto.UserInsertOrUpdateDto;
import com.fernando.win.dto.UserDto;
import com.fernando.win.repositories.RoleRepository;
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

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserDto insert(UserInsertOrUpdateDto dto){
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = repository.save(entity);
        return new UserDto(entity);
    }

    @Transactional
    public UserDto update(Long id, UserInsertOrUpdateDto userInsertOrUpdateDto){
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id " + id + " não encontrado."));
        copyDtoToEntity(userInsertOrUpdateDto, entity);
        entity.setPassword(passwordEncoder.encode(userInsertOrUpdateDto.getPassword()));
        entity = repository.save(entity);
        return new UserDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<UserDto> findAllPageable(Pageable pageable) {
        Page<User> users = repository.findAll(pageable);
        return users.map(UserDto::new);
    }
    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id " + id + " não encontrado."));
        return new UserDto(entity);
    }

    public void copyDtoToEntity(UserInsertOrUpdateDto dto, User entity){
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.getRoles().clear();
        for(RoleDto roleDto : dto.getRoles()){
            Role role = roleRepository.getOne(roleDto.getId());
            entity.getRoles().add(role);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        if(user == null){
            logger.error("Usuário não encontrado: " + username);
            throw new UsernameNotFoundException("E-mail não encontrado.");
        }
        logger.info("Usuário logado: " + username);
        return user;
    }

}
