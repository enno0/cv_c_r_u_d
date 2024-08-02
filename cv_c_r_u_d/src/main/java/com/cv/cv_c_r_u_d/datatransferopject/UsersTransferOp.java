package com.cv.cv_c_r_u_d.datatransferopject;

import com.cv.cv_c_r_u_d.customannotation.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class UsersTransferOp {

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password should have at least 8 characters")
    @ValidPassword
    private String password;

    @NotNull(message = "MobilePhone cannot be null")
    @Size(min = 11, message = "mobilePhone should have at least 11 characters")
    private String mobilePhone;

}
