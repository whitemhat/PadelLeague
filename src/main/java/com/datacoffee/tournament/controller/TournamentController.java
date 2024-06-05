package com.datacoffee.tournament.controller;

import com.datacoffee.tournament.constants.TournamentsConstants;
import com.datacoffee.tournament.dto.ErrorResponseDto;
import com.datacoffee.tournament.dto.ResponseDto;
import com.datacoffee.tournament.dto.TournamentDto;
import com.datacoffee.tournament.entity.Tournament;
import com.datacoffee.tournament.service.ITournamnetService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eazy Bytes
 */

@Tag(
        name = "CRUD REST APIs for Tournament in DataCoffee",
        description = "CRUD REST APIs in DataCoffee to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class TournamentController {

    private static final Logger logger = LoggerFactory.getLogger(TournamentController.class);

    private final ITournamnetService iTournamentService;

    public TournamentController(ITournamnetService iTournamentService) {
        this.iTournamentService = iTournamentService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Operation(
            summary = "Create Tournament REST API",
            description = "REST API to create new Tournament &  Tournament inside DataCoffee"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createTournament(@Valid @RequestBody TournamentDto tournamentDto) {
        iTournamentService.createTournament(tournamentDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(TournamentsConstants.STATUS_201, TournamentsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Tournament Details REST API",
            description = "REST API to fetch Tournament &  Tournament details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<Tournament> fetchTournamentDetails(@RequestParam
                                                               @Pattern(regexp="(^$|[0-9]{10})",message = "Tournament Name must be 10 digits")
                                                               String tournamentName) {

        Tournament customerDto = iTournamentService.fetchTournament(tournamentName);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update Tournament Details REST API",
            description = "REST API to update Tournament &  Tournament details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateTournamentDetails(@Valid @RequestBody TournamentDto tournamentDto) {
        boolean isUpdated = iTournamentService.updateTournament(tournamentDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(TournamentsConstants.STATUS_200, TournamentsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(TournamentsConstants.STATUS_417, TournamentsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Tournament & Tournament Details REST API",
            description = "REST API to delete Tournament &  Tournament details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteTournamentDetails(@RequestParam
                                                                @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                                Long tournamentId) {
        boolean isDeleted = iTournamentService.deleteTournamentById(tournamentId);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(TournamentsConstants.STATUS_200, TournamentsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(TournamentsConstants.STATUS_417, TournamentsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @Retry(name = "getBuildInfo",fallbackMethod = "getBuildInfoFallback")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        logger.debug("getBuildInfo() method Invoked");
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(buildVersion);
    }

    public ResponseEntity<String> getBuildInfoFallback(Throwable throwable) {
        logger.debug("getBuildInfoFallback() method Invoked");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("0.9");
    }

    @Operation(
            summary = "Get Java version",
            description = "Get Java versions details that is installed into accounts microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @RateLimiter(name= "getJavaVersion", fallbackMethod = "getJavaVersionFallback")
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    public ResponseEntity<String> getJavaVersionFallback(Throwable throwable) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Java 17");
    }
}
