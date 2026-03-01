package com.muse.server.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@lombok.Setter
@lombok.Getter
public class RegisterRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Username is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    public RegisterRequest() {}
}
