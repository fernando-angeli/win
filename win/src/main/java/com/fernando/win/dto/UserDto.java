package com.fernando.win.dto;

import com.fernando.win.domain.User;
import lombok.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "Campo obrigatório.")
    private String name;

    @Email(message = "E-mail inválido.")
    private String email;

    Set<RoleDto> roles = new HashSet<>();

    public UserDto(User entity){
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDto(role)));
    }

}
