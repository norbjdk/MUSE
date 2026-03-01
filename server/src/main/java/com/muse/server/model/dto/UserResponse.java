package com.muse.server.model.dto;

@lombok.Setter
@lombok.Getter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String role;

    public UserResponse() {}
}
