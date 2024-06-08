package com.cv.cv_c_r_u_d.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "cv_c_r_u_d")
public class cvRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String department;
    private String city;
    private String country;
    private String yearOfBirth;

    public cvRegistration(String name, String email, String phone, String department, String city, String country,
            String yearOfBirth) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.city = city;
        this.country = country;
        this.yearOfBirth = yearOfBirth;
    }

}
