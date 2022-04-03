package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false, unique = true)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = true, unique = true)
    private String email;

    @ManyToOne(optional = false)
    private Town town;

    /**
     * Agent
     * •	id – accepts integer values, a primary identification field, an auto incremented field.
     * •	first name – accepts char sequences as values where their character length value higher than or equal to 2. The values are unique in the database.
     * •	last name – accepts char sequences as values where their character length value higher than or equal to 2.
     * •	email – an email – (must contains ‘@’ and ‘.’ – dot). The email of a seller is unique.
     * •	Constraint: The agents table has а relation with the towns table.
     */

    public Agent() {
    }

    public Agent(long id, String firstName, String lastName, String email, Town town) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.town = town;
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

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
