package com.commute.controller;

import com.commute.domain.Team;
import com.commute.dto.TeamRequest;
import com.commute.dto.TeamResponse;
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
    public ResponseEntity<Team> saveTeam(@RequestBody TeamRequest request) {
        Team team = teamService.saveTeam(request);

        return ResponseEntity.ok(team);
    }

    @GetMapping
    public ResponseEntity<List<TeamResponse>> getAllTeams() {
        List<TeamResponse> teamsInfo = teamService.getAllTeamsInfo();

        return ResponseEntity.ok(teamsInfo);
    }
}
