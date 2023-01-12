package com.fernando.win.services.validation;

import com.fernando.win.dto.UserInsertDto;
import com.fernando.win.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDto> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserInsertValid ann){
    }

    @Override
    public boolean isValid(UserInsertDto userInsertDto, ConstraintValidatorContext context) {



        return false;
    }
}
