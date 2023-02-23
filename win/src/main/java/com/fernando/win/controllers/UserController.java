package com.fernando.win.controllers;

import com.fernando.win.dto.UserDto;
import com.fernando.win.dto.UserInsertOrUpdateDto;
import com.fernando.win.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserDto> insert(@Valid @RequestBody UserInsertOrUpdateDto dto){
        UserDto newUserDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUserDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newUserDto);
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> findAllPageable(Pageable pageable){
        Page<UserDto> list = service.findAllPageable(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserInsertOrUpdateDto userInsertOrUpdateDto){
        return ResponseEntity.ok().body(service.update(id, userInsertOrUpdateDto));
    }
}
