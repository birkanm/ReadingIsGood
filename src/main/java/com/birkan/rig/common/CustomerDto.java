package com.birkan.rig.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CustomerDto {

    private Long pkid;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "First name must not be less than 2 characters")
    private String name;

    @NotNull(message = "Surname cannot be null")
    @Size(min = 2, message = "Last name must not be less than 2 characters")
    private String surname;

    @Email
    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Phone cannot be null")
    @Size(min = 3, max = 14, message = "Phone length should be between 3-14 characters ")
    private String phone;
}
