package com.example.football.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int population;

    @Column(name = "travel_guide", columnDefinition = "TEXT")
    private String travelGuide;

    /**
     * Town
     * •	id – accepts integer values, a primary identification field, an auto incremented field.
     * •	name – accepts char sequences as values where their character length value higher than or equal to 2. The values are unique in the database.
     * •	population – accepts number values (must be a positive number), 0 as a value is exclusive.
     * •	travel guide – a long and detailed description of all known places with a character length value higher than or equal to 10.
     */

    public Town() {
    }

    public Town(long id, String name, int population, String travelGuide) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.travelGuide = travelGuide;
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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
