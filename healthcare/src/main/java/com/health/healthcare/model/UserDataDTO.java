package com.health.healthcare.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDataDTO {

    private Long id;
    private Long sleep;
    private Long water;
    private Long steps;
    private LocalDateTime createDate;
    private Long user;

}
