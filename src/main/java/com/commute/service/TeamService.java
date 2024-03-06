package com.commute.service;

import com.commute.domain.Team;
import com.commute.dto.TeamRegistrationRequest;
import com.commute.dto.TeamInfoResponse;
import com.commute.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public Team saveTeam(TeamRegistrationRequest request) {
        return teamRepository.save(new Team(request.getName()));
    }

    public List<TeamInfoResponse> getAllTeamsInfo() {
        return teamRepository.findAllTeamInfo();
    }
}
