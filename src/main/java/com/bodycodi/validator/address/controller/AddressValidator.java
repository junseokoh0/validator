package com.bodycodi.validator.address.controller;

import com.bodycodi.validator.address.vo.Address;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AddressValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Address.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Address address = (Address) target;

        final int zipCodeLength = 5;

        if (String.valueOf(address.getZipCode()).length() != zipCodeLength) {
            errors.rejectValue(
                    "zipCode",
                    "zipcode.length",
                    "zipCodeLength가 규칙에 어긋납니다. 규칙 5, input :" + String.valueOf(address.getZipCode()).length()
            );
        }
    }
}
