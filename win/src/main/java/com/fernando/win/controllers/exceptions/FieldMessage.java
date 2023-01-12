package com.fernando.win.controllers.exceptions;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldMessage {

    private String fieldName;
    private String fieldMessage;

}
