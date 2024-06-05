package com.datacoffee.tournament.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class TournamentDto {

    @NotEmpty(message = "TournamentName can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "TournamentName must be 10 digits")
    @Schema(
            description = "Name Of your Tournament", example = "King's League"
    )
    private String name;

    @NotEmpty(message = "Start Date can not be a null or empty")
    @Schema(
            description = "Start Date of Tournament"
    )
    private LocalDateTime startTs;

    @NotEmpty(message = "Close Date can not be a null or empty")
    @Schema(
            description = "CLose Date of Tournament"
    )
    private LocalDateTime closeTs;

    //@NotEmpty(message = "Activity ID can not be a null or empty")
    @Schema(
            description = "Activity Identifier"
    )
    private Long activityId;

    @Schema(
            description = "Activity Identifier"
    )
    private LocalDateTime createdTs;

    @Schema(
            description = "Insert User Identifier"
    )
    private Long insertUserId;

    @Schema(
            description = "Organizer Identifier"
    )
    private Long organizerId;

    @Schema(
            description = "Number Of Players in Tournament"
    )
    private Integer playerCapability;
}
