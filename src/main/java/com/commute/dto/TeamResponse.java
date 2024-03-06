package com.commute.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TeamResponse {

    private String name;
    private String manager;
    private Long memberCount;

}
