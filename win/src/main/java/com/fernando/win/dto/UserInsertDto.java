package com.fernando.win.dto;

import com.fernando.win.services.validation.UserInsertValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@UserInsertValid
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserInsertDto extends UserDto{

    private String password;

}
