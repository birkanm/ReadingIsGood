package com.birkan.rig.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {

    private Long pkid;

    private String name;

    private String surname;

    private String email;

    private String phone;
}
