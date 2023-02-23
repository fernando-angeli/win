package com.fernando.win.dto;

import com.fernando.win.domain.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Long id;
    private String authority;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }
}
