package com.fernando.win.dto;

import com.fernando.win.services.validation.UserInsertValid;
import lombok.*;

@UserInsertValid
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInsertOrUpdateDto extends UserDto {

    private String password;

}
