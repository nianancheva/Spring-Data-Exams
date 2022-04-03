package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class TownDTO implements Serializable {

    @Size(min = 2)
    @NotNull
    private String townName;

    @Positive
    @NotNull
    private int population;

    /**
     * {
     *     "townName": "Matingain",
     *     "population": 2162142
     *   }
     */

    public TownDTO() {
    }

    public TownDTO(String townName, int population) {
        this.townName = townName;
        this.population = population;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
