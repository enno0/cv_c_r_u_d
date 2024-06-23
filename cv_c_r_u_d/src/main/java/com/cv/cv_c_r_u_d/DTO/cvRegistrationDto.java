package com.cv.cv_c_r_u_d.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class cvRegistrationDto {

    private String name;
    private String email;
    private String phone;
    private String department;
    private String city;
    private String country;
    private String yearOfBirth;

}
