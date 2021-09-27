package com.bodycodi.validator.address.vo;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @NotNull
    private String defaultAddress;
    @NotNull
    private String detailAddress;
    private Integer zipCode;
}
