package com.bodycodi.validator.person.controller;

import com.bodycodi.validator.ErrorLogging;
import com.bodycodi.validator.address.controller.AddressValidator;
import com.bodycodi.validator.person.vo.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    @InitBinder("personDummy")
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new PersonValidator(new AddressValidator()));
    }

    @InitBinder("person")
    protected void initBinderDummy(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new PersonValidator(new AddressValidator()));
    }

    @PutMapping("/valid")
    @ResponseStatus(HttpStatus.OK)
    public void valid(@RequestBody @Validated Person person, Errors errors, BindingResult bindingResult) {
        ErrorLogging.log(errors, getClass());
    }

    @PutMapping("/validated-step-one")
    @ResponseStatus(HttpStatus.OK)
    public void validatedStepOne(@RequestBody @Validated(Person.ValidationStepOne.class) Person person, Errors errors, BindingResult bindingResult) {
        ErrorLogging.log(errors, getClass());
    }

    @PutMapping("/validated-step-two")
    @ResponseStatus(HttpStatus.OK)
    public void validatedStepTwo(@RequestBody @Validated(Person.ValidationStepTwo.class) Person person, Errors errors, BindingResult bindingResult) {
        ErrorLogging.log(errors, getClass());
    }

}
