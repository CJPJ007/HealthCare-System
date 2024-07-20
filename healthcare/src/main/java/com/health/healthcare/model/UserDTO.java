package com.health.healthcare.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {

    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

    private Long age;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String password;

    private LocalDateTime createdDate;

    private LocalDateTime updateDate;

}
