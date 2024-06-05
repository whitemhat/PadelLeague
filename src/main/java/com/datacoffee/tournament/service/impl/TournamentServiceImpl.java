package com.datacoffee.tournament.service.impl;

import com.datacoffee.tournament.constants.TournamentsConstants;
import com.datacoffee.tournament.dto.TournamentDto;
import com.datacoffee.tournament.entity.Tournament;
import com.datacoffee.tournament.mapper.TournamentMapper;
import com.datacoffee.tournament.service.ITournamnetService;
import com.datacoffee.tournament.exception.ResourceNotFoundException;
import com.datacoffee.tournament.repository.TournamentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class TournamentServiceImpl implements ITournamnetService {

    private TournamentRepository tournamentsRepository;

    /**
     * @param tournamentDto - TournamentDto Object
     */
    @Override
    public void createTournament(TournamentDto tournamentDto) {
        Tournament tournament = TournamentMapper.mapToTournament(tournamentDto, new Tournament());
        tournamentsRepository.save(tournament);
    }

    /**
     * @param name - Input Name
     * @return Tournaments Details based on a given mobileNumber
     */
    @Override
    public Tournament fetchTournament(String name) {
        return tournamentsRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException("Tournament", "mobileNumber", name)
        );
    }

    /**
     * @param tournamentDto - TournamentDto Object
     * @return boolean indicating if the update of Tournament details is successful or not
     */
    @Override
    public boolean updateTournament(TournamentDto tournamentDto) {
        boolean isUpdated = false;

            Tournament tournaments = tournamentsRepository.findByName(tournamentDto.getName()).orElseThrow(
                    () -> new ResourceNotFoundException("Tournament", "TournamentName", tournamentDto.getName().toString())
            );
            TournamentMapper.mapToTournament(tournamentDto, tournaments);
            tournamentsRepository.save(tournaments);
            isUpdated = true;

        return  isUpdated;
    }

    /**
     * @param tournamentId - TournamentId
     * @return boolean indicating if the delete of Tournament details is successful or not
     */
    @Override
    public boolean deleteTournamentById(Long tournamentId) {
        Tournament tournament = tournamentsRepository.findByTournamentId(tournamentId).orElseThrow(
                () -> new ResourceNotFoundException("Tournament", "tournamentId", tournamentId.toString())
        );
        tournamentsRepository.deleteByTournamentId(tournament.getTournamentId());
        return true;
    }


}
