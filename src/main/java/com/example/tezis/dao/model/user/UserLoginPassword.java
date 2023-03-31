package com.example.tezis.dao.model.user;

import com.example.tezis.util.user.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class UserLoginPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    private String username;

    private String password;

    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private long validUntil;

    public UserLoginPassword(UserRole role) {
        this.id = 0;
        this.enabled = true;
        this.role = role;
        this.validUntil = new Date().getTime() + role.getValidationPeriod();

    }

    public UserLoginPassword(String nameAndSurname) {
        this(UserRole.USER);
        username = nameAndSurname;
        password = "mustBeChanged";
    }

    public UserLoginPassword(String username, String password, UserRole role) {
        this(role);
        this.username = username;
        this.password = password;
    }

    public void refreshValidity() {
        long ttl = 0;
        if (enabled && validUntil == 0) {
            ttl = new Date().getTime() + getRole().getValidationPeriod();
        } else if (enabled) {

            ttl = validUntil + role.getValidationPeriod();
        }

        validUntil = ttl;
    }
}
