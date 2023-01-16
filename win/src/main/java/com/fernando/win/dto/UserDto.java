package com.fernando.win.dto;

import com.fernando.win.domain.User;
import lombok.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer id;
    @NotBlank(message = "Campo obrigatório.")
    private String name;
    @Email(message = "E-mail inválido.")
    private String email;

    public UserDto(User entity){
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
    }

}
