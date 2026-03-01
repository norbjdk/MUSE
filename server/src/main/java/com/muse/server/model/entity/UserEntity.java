package com.muse.server.model.entity;

import jakarta.persistence.*;

@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "users")
public final class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    /**
     * expected roles: listener, artist, admin
     */

    @Column(nullable = false)
    private String role;

    public UserEntity() {}
}
