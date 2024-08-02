package com.cv.cv_c_r_u_d.models;

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
@Table(name = "tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "title cannot be null")
    @Size(min = 2, message = "title should have at least 2 characters")
    private String title;

    @NotNull(message = "description cannot be null")
    @Size(min = 2, message = "description should have at least 2 characters")
    private String description;

    @NotNull(message = "status cannot be null")
    @Size(min = 2, message = "status should have at least 2 characters")
    private String status;

    public Tasks(
            @NotNull(message = "title cannot be null") @Size(min = 2, message = "title should have at least 2 characters") String title,
            @NotNull(message = "description cannot be null") @Size(min = 2, message = "description should have at least 2 characters") String description,
            @NotNull(message = "status cannot be null") @Size(min = 2, message = "status should have at least 2 characters") String status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

}
