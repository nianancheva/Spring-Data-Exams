package com.example.football.models.dto;

import javax.validation.constraints.NotNull;

public class TownNameDTO {

    @NotNull
    private String name;

    public TownNameDTO() {
    }

    public TownNameDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
