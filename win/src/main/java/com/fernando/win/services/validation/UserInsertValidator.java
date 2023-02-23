package com.fernando.win.services.validation;

import com.fernando.win.controllers.exceptions.FieldMessage;
import com.fernando.win.domain.User;
import com.fernando.win.dto.UserInsertOrUpdateDto;
import com.fernando.win.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertOrUpdateDto> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserInsertValid ann){
    }

    @Override
    public boolean isValid(UserInsertOrUpdateDto dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        User user = repository.findByEmail(dto.getEmail());
        if(user != null){
            list.add(new FieldMessage("email", "E-mail já cadastrado."));
        }

        for(FieldMessage e : list){
            context.disableDefaultConstraintViolation();;
            context
                    .buildConstraintViolationWithTemplate(e.getFieldMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}
