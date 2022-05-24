package com.example.springday03.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Employee {
    @NotNull
    @Size(min=3, message = "Employee ID's length must be more than 2 ")
    private String empID;

    @NotNull
    @Size(min=5, message = "Name length must be longer than 4")
    private String empName;

    @NotNull @PositiveOrZero
    @Range(min=25)
    private Integer age;

//    @AssertTrue(message = "On leave must be false")
    private Boolean onLeave = false;

    @NotNull
    @Pattern(regexp = "[0-3]{1}[0-9]{1}/[0-3]{1}[0-9]{1}/[1-2]{1}[0-9]{1}[0-9]{1}[0-9]{1}")
    private String yearOfEmployment;

    @NotNull
    private Integer annualLeave;


}
