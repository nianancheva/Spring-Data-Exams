package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "town_name", nullable = false, unique = true)
    private String townName;

    @Column(nullable = false)
    private int population;

    /**
     * Town
     * •	id – accepts integer values, a primary identification field, an auto incremented field.
     * •	town name – accepts char sequences as values where their character length value is higher than or equal to 2. The values are unique in the database.
     * •	population – accepts number values (must be positive), 0 as a value is exclusive.
     */

    public Town() {
    }

    public Town(long id, String townName, int population) {
        this.id = id;
        this.townName = townName;
        this.population = population;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
