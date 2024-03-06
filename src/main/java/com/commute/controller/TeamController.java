package com.commute.controller;

import com.commute.domain.Team;
import com.commute.dto.TeamRegistrationRequest;
import com.commute.dto.TeamInfoResponse;
import com.commute.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/teams")
@RestController
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/team")
    public ResponseEntity<Team> saveTeam(@RequestBody TeamRegistrationRequest request) {
        Team team = teamService.saveTeam(request);

        return ResponseEntity.ok(team);
    }

    @GetMapping
    public ResponseEntity<List<TeamInfoResponse>> getAllTeams() {
        List<TeamInfoResponse> teamsInfo = teamService.getAllTeamsInfo();

        return ResponseEntity.ok(teamsInfo);
    }
}
