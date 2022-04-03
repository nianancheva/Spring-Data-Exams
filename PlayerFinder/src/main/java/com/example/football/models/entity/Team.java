package com.example.football.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "stadium_name", nullable = false)
    private String stadiumName;

    @Column(name = "fan_base", nullable = false)
    private int fanBase;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String history;

    @ManyToOne(optional = false)
    private Town town;

    /**
     * Team
     * •	id – accepts integer values, a primary identification field, an auto incremented field.
     * •	name – accepts char sequences as values where their character length value higher than or equal to 3. The values are unique in the database.
     * •	stadium name – accepts char sequences as values where their character length value higher than or equal to 3.
     * •	fan base – accepts number values that are more than or equal to 1000.
     * •	history – a long and detailed description of team's history with a character length value higher than or equal to 10.
     * o	Note: The teams table has relation with the towns table.
     */

    public Team() {
    }

    public Team(long id, String name, String stadiumName, int fanBase, String history, Town town) {
        this.id = id;
        this.name = name;
        this.stadiumName = stadiumName;
        this.fanBase = fanBase;
        this.history = history;
        this.town = town;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
