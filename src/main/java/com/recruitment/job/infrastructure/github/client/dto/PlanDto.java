package com.recruitment.job.infrastructure.github.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanDto {
    private String name;
    private int space;
    private int private_repos;
    private int collaborators;
}
