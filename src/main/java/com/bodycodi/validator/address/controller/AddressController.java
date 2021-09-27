package com.bodycodi.validator.address.controller;

import com.bodycodi.validator.ErrorLogging;
import com.bodycodi.validator.address.vo.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController {

    @InitBinder("address")
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new AddressValidator());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/valid")
    public void valid(@Valid Address address, Errors errors, BindingResult bindingResult) {
        ErrorLogging.log(errors, getClass());
    }

}
