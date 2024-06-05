package com.datacoffee.tournament.mapper;

import com.datacoffee.tournament.dto.TournamentDto;
import com.datacoffee.tournament.entity.Tournament;

import java.time.LocalDateTime;

public class TournamentMapper {

    public static Tournament mapToTournament(TournamentDto accountsDto, Tournament accounts) {
        accounts.setName(accountsDto.getName());
        accounts.setStartTs(accountsDto.getStartTs());
        accounts.setCloseTs(accountsDto.getCloseTs());
        accounts.setActivityId(accountsDto.getActivityId());
        accounts.setCreatedTs(LocalDateTime.now());
        accounts.setInsertUserId(accountsDto.getInsertUserId());
        accounts.setOrganizerId(accountsDto.getOrganizerId());
        accounts.setPlayerCapability(accountsDto.getPlayerCapability());
        return accounts;
    }

}
