package com.example.football.models.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private PlayerPosition position;

    @ManyToOne(optional = false)
    private Town town;

    @ManyToOne(optional = false)
    private Team team;

    @OneToOne(optional = false)
    private Stat stat;

    /**
     * Player
     * •	id – accepts integer values, a primary identification field, an auto incremented field.
     * •	first name – accepts char sequences as values where their character length value higher than 2.
     * •	last name – accepts char sequences as values where their character length value higher than 2.
     * •	email – accepts valid email addresses (must contains '@' and '.' – a dot). The values are unique in the database.
     * •	birth date – a date in the "dd/MM/yyyy" format.
     * •	position – one of the following – ATT, MID, DEF.
     * o	Note: The players table has relations with the towns, teams and stats tables.
     */

    public Player() {
    }

    public Player(long id, String firstName, String lastName, String email, LocalDate birthDate, PlayerPosition position, Town town, Team team, Stat stat) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.position = position;
        this.town = town;
        this.team = team;
        this.stat = stat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {

        List<String> str = new ArrayList<>();

        /**
         * Player - {firstName} {lastName}
         * Position - {position name}
         * Team - {team name}
         * Stadium - {stadium name}
         */

        String player = String.format("Player - %s %s", this.getFirstName(), getLastName());
        String position = String.format("\tPosition - %s", this.getPosition());
        String team = String.format("\tTeam - %s", this.getTeam().getName());
        String stadium = String.format("\tStadium - %s", this.getTeam().getStadiumName());

        str.add(player);
        str.add(position);
        str.add(team);
        str.add(stadium);

        return String.join("\n", str);
    }
}
