package com.commute.repository;

import com.commute.domain.LeavePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeavePolicyRepository extends JpaRepository<LeavePolicy, Long> {
    Optional<LeavePolicy> findByTeamId(Long teamId);
}
