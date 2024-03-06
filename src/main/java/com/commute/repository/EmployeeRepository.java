package com.commute.repository;


import com.commute.domain.Employee;
import com.commute.dto.EmployeeInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new com.commute.dto.EmployeeInfoResponse(e.name, t.name, " +
            "CASE WHEN e.role = 'MANAGER' THEN 'MANAGER' ELSE 'MEMBER' END, " +
            "e.birthDay, e.entryDate) " +
            "FROM Employee e LEFT JOIN e.team t")
    List<EmployeeInfoResponse> findAllEmployeeInfo();


}
