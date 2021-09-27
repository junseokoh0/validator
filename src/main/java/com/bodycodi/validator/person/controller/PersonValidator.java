package com.bodycodi.validator.person.controller;

import com.bodycodi.validator.address.vo.Address;
import com.bodycodi.validator.person.vo.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {

    private final Validator addressValidator;

    public PersonValidator(Validator addressValidator) {
        if (addressValidator == null) {
            throw new IllegalArgumentException("addressValidator 는 null 일 수 없습니다.");
        }
        if (!addressValidator.supports(Address.class)) {
            throw new IllegalArgumentException("addressValidator는 Address 크래스를 지원해야 합니다.");
        }
        this.addressValidator = addressValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "id", "id.empty");
        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.null", "firstName 이 비어있음.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "latName.whitespace");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "age.whitespace", "age가 비었음");

        Person person = (Person) target;
        if (person.getAge() > 20) {
            errors.rejectValue("age", "age too many.");
        }

        try {
            errors.pushNestedPath("address");
            ValidationUtils.invokeValidator(this.addressValidator, person.getAddress(), errors);
        } finally {
            errors.popNestedPath();
        }
    }
}
