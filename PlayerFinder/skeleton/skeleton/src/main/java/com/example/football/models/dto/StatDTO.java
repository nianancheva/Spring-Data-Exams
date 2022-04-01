package com.example.football.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatDTO {

    @XmlElement
    @Positive
    @NotNull
    private float passing;

    @XmlElement
    @Positive
    @NotNull
    private float shooting;

    @XmlElement
    @Positive
    @NotNull
    private float endurance;

    /**
     * <stat>
     *         <passing>5.75</passing>
     *         <shooting>77.64</shooting>
     *         <endurance>45.09</endurance>
     *     </stat>
     */

    public StatDTO() {
    }

    public StatDTO(float passing, float shooting, float endurance) {
        this.passing = passing;
        this.shooting = shooting;
        this.endurance = endurance;
    }

    public float getPassing() {
        return passing;
    }

    public void setPassing(float passing) {
        this.passing = passing;
    }

    public float getShooting() {
        return shooting;
    }

    public void setShooting(float shooting) {
        this.shooting = shooting;
    }

    public float getEndurance() {
        return endurance;
    }

    public void setEndurance(float endurance) {
        this.endurance = endurance;
    }
}
