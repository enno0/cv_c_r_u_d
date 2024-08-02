package com.cv.cv_c_r_u_d.models;

import com.cv.cv_c_r_u_d.customannotation.CapitalCase;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole {
    public UserRole(
            @NotNull(message = "Name cannot be null") @Size(min = 2, message = "Name should have at least 2 characters") String name,
            @NotNull(message = "Role cannot be null") @Size(min = 2, message = "Role should have at least 2 characters") String role) {
        this.name = name;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @NotNull(message = "Role cannot be null")
    @Size(min = 2, message = "Role should have at least 2 characters")
    @CapitalCase(message = "Role must be in capital case")
    private String role;
}