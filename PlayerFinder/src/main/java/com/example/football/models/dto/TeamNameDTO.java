package com.example.football.models.dto;

import javax.validation.constraints.NotNull;

public class TeamNameDTO {

    @NotNull
    private String name;

    public TeamNameDTO() {
    }

    public TeamNameDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
