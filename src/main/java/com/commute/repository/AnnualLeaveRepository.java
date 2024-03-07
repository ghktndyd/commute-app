package com.commute.repository;

import com.commute.domain.AnnualLeave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnualLeaveRepository extends JpaRepository<AnnualLeave, Long> {
}
