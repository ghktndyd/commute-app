package com.commute.service;

import com.commute.domain.Team;
import com.commute.dto.TeamRequest;
import com.commute.dto.TeamResponse;
import com.commute.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public Team saveTeam(TeamRequest request) {
        return teamRepository.save(new Team(request.getName()));
    }

    public List<TeamResponse> getAllTeamsInfo() {
        return teamRepository.findAllTeamInfo();
    }
}
