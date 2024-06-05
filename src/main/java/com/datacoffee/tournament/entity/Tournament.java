package com.datacoffee.tournament.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "dyn_Tournaments")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Tournament {//extends  BaseEntity {

    @Id
    @Column(name="tournament_id")
    private Long tournamentId;

    @Column(name="name")
    private String name;

    @Column(name="start_ts")
    private LocalDateTime startTs;

    @Column(name="close_ts")
    private LocalDateTime closeTs;

    @Column(name="activity_id")
    private Long activityId;

    @Column(name="created_ts")
    private LocalDateTime createdTs;

    @Column(name="insert_user_id")
    private Long insertUserId;

    @Column(name="organizer_id")
    private Long organizerId;

    @Column(name="player_capability")
    private Integer playerCapability;

}
