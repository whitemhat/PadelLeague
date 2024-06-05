package com.datacoffee.tournament.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "reg_Users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name="user_id")
    private Long userId;

    @Column(name="tournament_id")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="role_tag")
    private String roleTag;

    @Column(name="enabled")
    private Boolean enabled;

    @Column(name="created_ts")
    private LocalDateTime createdTs;

    @Column(name="person_id")
    private Long personId;

    @Column(name="company_id")
    private Long companyId;
}
