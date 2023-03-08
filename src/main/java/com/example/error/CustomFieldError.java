package com.example.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomFieldError {

    private String field;
    private String value;
    private String reason;

    public CustomFieldError(String field, String value, String reason) {
        this.field = field;
        this.value = value;
        this.reason = reason;
    }

    public static List<CustomFieldError> of(String field, String value, String reason) {
        List<CustomFieldError> errors = new ArrayList<>();
        errors.add(new CustomFieldError(field, value, reason));
        return errors;
    }

    public static List<CustomFieldError> of(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return fieldErrors.stream()
                .map(err -> new CustomFieldError(
                        err.getField(),
                        err.getRejectedValue() == null ? "" : err.getRejectedValue().toString(),
                        err.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}
