package com.example.football.models.dto;

import com.example.football.models.entity.PlayerPosition;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerDTO {

    @XmlElement(name = "first-name")
    @Size(min = 2)
    @NotNull
    private String firstName;

    @XmlElement(name = "last-name")
    @Size(min = 2)
    @NotNull
    private String lastName;

    @XmlElement
    @Email
    @NotNull
    private String email;

    @XmlElement(name = "birth-date")
    //XmlJavaTypeAdapter()
    @NotNull
    private String birthDate;

    @Enumerated(value = EnumType.ORDINAL)
    @NotNull
    private PlayerPosition position;

    @XmlElement
    @NotNull
    private TownNameDTO town;

    @XmlElement
    @NotNull
    private TeamNameDTO team;

    @XmlElement
    @NotNull
    private StatIdDTO stat;

    /**
     * <player>
     *         <first-name>L</first-name>
     *         <last-name>Smallbone</last-name>
     *         <email>lsmallbone0@hubpages.com</email>
     *         <birth-date>21/02/1979</birth-date>
     *         <position>ATT</position>
     *         <town>
     *             <name>Kazan</name>
     *         </town>
     *         <team>
     *             <name>McGlynn</name>
     *         </team>
     *         <stat>
     *             <id>53</id>
     *         </stat>
     *     </player>
     */

    public PlayerDTO() {
    }

    public PlayerDTO(String firstName, String lastName, String email, String birthDate, PlayerPosition position, TownNameDTO town, TeamNameDTO team, StatIdDTO stat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.position = position;
        this.town = town;
        this.team = team;
        this.stat = stat;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public TownNameDTO getTown() {
        return town;
    }

    public void setTown(TownNameDTO town) {
        this.town = town;
    }

    public TeamNameDTO getTeam() {
        return team;
    }

    public void setTeam(TeamNameDTO team) {
        this.team = team;
    }

    public StatIdDTO getStat() {
        return stat;
    }

    public void setStat(StatIdDTO stat) {
        this.stat = stat;
    }
}
