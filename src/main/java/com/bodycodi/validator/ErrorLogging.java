package com.bodycodi.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;

public class ErrorLogging {

    public static void log(Errors errors, Class clazz) {
        final Logger log = LoggerFactory.getLogger(clazz);

        log.info(">>>>>>>>>> errorsCount ::: {}", errors.getErrorCount());

        errors.getFieldErrors().forEach(
                fieldError -> {
                    log.info(">>>>>>>>>> fieldError");
                    log.info(">>>>> Field ::: {}", fieldError.getField());
                    log.info(">>>>> Code ::: {}", fieldError.getCode());
                    log.info(">>>>> DefaultMessage ::: {}", fieldError.getDefaultMessage());
                }
        );
    }
}
