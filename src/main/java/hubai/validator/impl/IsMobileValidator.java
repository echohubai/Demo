package hubai.validator.impl;


import hubai.utils.ValidatorUtil;
import hubai.validator.IsMobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 手机号码校验器
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    private boolean required=false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required= constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (required){
            return ValidatorUtil.isMobile(value);
        }
        return true;
    }
}
