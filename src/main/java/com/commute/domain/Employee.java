package com.commute.domain;

import com.commute.domain.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDate entryDate;

    private LocalDate birthDay;

    private int remainingAnnualLeave;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public int subtractOneAnnualLeave() {
        this.remainingAnnualLeave = this.getRemainingAnnualLeave() - 1;
        return remainingAnnualLeave;
    }

}
