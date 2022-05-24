package com.example.springday03.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class Ride {
    @NotNull
    @Size(min=3, message = "Ride ID's length must be more than 2 ")
    private String rideID;

    @NotNull
    @Size(min=5, message = "Name length must be longer than 4")
    private String rideName;

    @NotNull
    @Pattern(regexp = "(Rollercoaster|thriller|water)", message = "Please choose from Rollercoaster, thriller or water")
    private String rideType;

    @NotNull(message = "Please enter a number of tickets")
    @PositiveOrZero
//    @Pattern(regexp = "[\\d]")
    private Integer tickets;

    @NotNull(message = "Please enter a valid price.")
    @PositiveOrZero
//    @Pattern(regexp = "/^\\d+\\.\\d+$/")
    private Double price;



}
