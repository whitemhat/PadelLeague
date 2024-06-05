package com.datacoffee.tournament.repository;

import com.datacoffee.tournament.entity.Tournament;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    Optional<Tournament> findByTournamentId(Long tournamentId);

    Optional<Tournament> findByName(String tournamentName);

    @Transactional
    @Modifying
    void deleteByTournamentId(Long tournamentId);

}
