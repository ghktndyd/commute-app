package com.commute.domain;

import com.commute.domain.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
