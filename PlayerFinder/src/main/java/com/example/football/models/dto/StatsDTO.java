package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatsDTO {

    @XmlElement(name = "stat")
    private List<StatDTO> stats;

    public StatsDTO() {
    }

    public StatsDTO(List<StatDTO> stats) {
        this.stats = stats;
    }

    public List<StatDTO> getStats() {
        return stats;
    }

    public void setStats(List<StatDTO> stats) {
        this.stats = stats;
    }
}
