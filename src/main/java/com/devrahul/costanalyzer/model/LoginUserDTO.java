package com.devrahul.costanalyzer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginUserDTO {
    private String email;
    private String password;
}
