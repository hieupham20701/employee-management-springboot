package com.codingclub.springbootdemo.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatorFile implements ConstraintValidator<ValidFile, MultipartFile> {
    @Override
    public void initialize(ValidFile constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {

        return isSupportedContentType(file.getContentType());
    }


    private boolean isSupportedContentType(String contentType) {
        if(contentType.equals("image/jpeg") || contentType.equals("image/png")){
            return true;
        }
        return false;
    }
}
