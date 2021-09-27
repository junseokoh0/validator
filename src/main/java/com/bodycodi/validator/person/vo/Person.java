package com.bodycodi.validator.person.vo;

import com.bodycodi.validator.address.vo.Address;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    public interface ValidationStepOne{}
    public interface ValidationStepTwo{}

    @NotNull(groups = { ValidationStepOne.class })
    private int id;
    @NotEmpty(groups = { ValidationStepOne.class })
    private String firstName;
    @NotBlank(groups = { ValidationStepTwo.class })
    private String lastName;
    @Max(groups = { ValidationStepTwo.class }, value = 10)
    @Min(groups = { ValidationStepTwo.class }, value = 5)
    private int age;
    @Null(groups = { ValidationStepTwo.class })
    private Address address;
    private LocalDateTime regDt;
}
