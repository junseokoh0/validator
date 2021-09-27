package com.bodycodi.validator.user.controller;

import com.bodycodi.validator.address.vo.Address;
import com.bodycodi.validator.person.vo.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void valid() throws Exception {
        requestPerson("/person/valid");
    }

    @Test
    void validatedStepOne() throws Exception {
        requestPerson("/person/validated-step-one");
    }

    @Test
    void validatedStepTwo() throws Exception {
        requestPerson("/person/validated-step-two");
    }

    private void requestPerson(final String requestUrl) throws Exception {
        final Person person = Person.builder().address(new Address()).build();

        mockMvc.perform(put(requestUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
