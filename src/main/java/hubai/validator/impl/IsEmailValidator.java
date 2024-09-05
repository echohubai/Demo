package hubai.validator.impl;


import hubai.utils.ValidatorUtil;
import hubai.validator.IsEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 邮箱校验器
 */
public class IsEmailValidator implements ConstraintValidator<IsEmail,String> {

    private boolean required=false;

    @Override
    public void initialize(IsEmail constraintAnnotation) {
        required= constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required){
            return ValidatorUtil.isEmail(value);
        }
        return true;
    }
}
