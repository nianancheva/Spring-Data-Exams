package com.example.football.models.dto;

import javax.validation.constraints.NotNull;

public class StatIdDTO {

    @NotNull
    private long id;

    public StatIdDTO() {
    }

    public StatIdDTO(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
