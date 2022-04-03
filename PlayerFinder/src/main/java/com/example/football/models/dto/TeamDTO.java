package com.example.football.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TeamDTO {

    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    @Size(min = 3)
    String stadiumName;

    @NotNull
    @Min(value = 1000)
    private int fanBase;

    @NotNull
    @Size(min = 10)
    private String history;

    @NotNull
    private String townName;

    /**
     * {
     *     "name": "Sc",
     *     "stadiumName": "Tera",
     *     "fanBase": 317723,
     *     "history": "Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam. Suspendisse potenti. Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum.",
     *     "townName": "Dallas"
     *   }
     */

    public TeamDTO() {
    }

    public TeamDTO(String name, String stadiumName, int fanBAse, String history, String townName) {
        this.name = name;
        this.stadiumName = stadiumName;
        this.fanBase = fanBAse;
        this.history = history;
        this.townName = townName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public int getFanBase() {
        return fanBase;
    }

    public void setFanBase(int fanBase) {
        this.fanBase = fanBase;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
