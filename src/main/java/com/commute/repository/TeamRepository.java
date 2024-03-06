package com.commute.repository;

import com.commute.domain.Team;
import com.commute.dto.TeamResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT new com.commute.dto.TeamResponse(t.name, " +
            "(SELECT e.name FROM Employee e WHERE e.isManager = true AND e.team = t), " +
            "(SELECT COUNT(e) FROM Employee e WHERE e.team = t)) " +
            "FROM Team t")
    List<TeamResponse> findAllTeamInfo();

}
