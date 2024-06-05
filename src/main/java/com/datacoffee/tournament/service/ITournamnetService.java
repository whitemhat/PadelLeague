package com.datacoffee.tournament.service;

import com.datacoffee.tournament.dto.TournamentDto;
import com.datacoffee.tournament.entity.Tournament;

public interface ITournamnetService {

    Tournament fetchTournament(String name);

    /**
     *
     * @param tournamentDto - TournamentDto Object
     */
    void createTournament(TournamentDto tournamentDto);

    /**
     *
     * @param tournamentDto - TournamentDto Object
     * @return boolean indicating if the update of Tournament details is successful or not
     */
    boolean updateTournament(TournamentDto tournamentDto);

    /**
     *
     * @param tournamentId - Input TournamentID
     * @return boolean indicating if the delete of Tournament details is successful or not
     */
    boolean deleteTournamentById(Long tournamentId);


}
