package exam.model.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "registered_on", nullable = false)
    private LocalDate registeredOn;

    @ManyToOne(optional = false)
    private Town town;

    /**
     * Customer
     * •	id – accepts integer values, a primary identification field, an auto incremented field.
     * •	first name – accepts char sequences as values where their character length value higher than or equal to 2.
     * •	last name – accepts char sequences as values where their character length value higher than or equal to 2.
     * •	email  – accepts valid email addresses (must contains '@' and '.' – a dot). The values are unique in the database.
     * •	registered on – a date when а customer registers in the shop.
     * •	Constraint: The customers table has а relation with the towns table.
     */

    public Customer() {
    }

    public Customer(long id, String firstName, String lastName, String email, LocalDate registeredOn, Town town) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registeredOn = registeredOn;
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

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
