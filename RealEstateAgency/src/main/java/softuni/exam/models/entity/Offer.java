package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "published_on", nullable = false)
    private LocalDate publishedOn;

    @ManyToOne(optional = false)
    private Apartment apartment;

    @ManyToOne(optional = false)
    private Agent agent;

    /**
     * Offer
     * •	id – accepts integer values, a primary identification field, an auto incremented field.
     * •	price – accepts a positive number.
     * •	published on – a date in the "dd/MM/yyyy" format.
     * •	Constraint: The offers table has a relation with the apartments table.
     * •	Constraint: The offers table has a relation with the agents table.
     */

    public Offer() {
    }

    public Offer(long id, BigDecimal price, LocalDate publishedOn, Apartment apartment, Agent agent) {
        this.id = id;
        this.price = price;
        this.publishedOn = publishedOn;
        this.apartment = apartment;
        this.agent = agent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    //•	"Agent {firstName} {lastName} with offer №{offerId}:
    //   		-Apartment area: {area}
    //   		--Town: {townName}
    //   		---Price: {price}$
    //. . . "

    @Override
    public String toString() {
        List<String> str = new ArrayList<>();

        String agent = String.format("Agent %s %s with offer №%d:",
                this.agent.getFirstName(), this.agent.getLastName(), this.getId());
        String apartment = String.format("\t-Apartment area: %.2f",
                this.getApartment().getArea());
        String town = String.format("\t--Town: %s",
                this.getApartment().getTown().getTownName());
        String price = String.format("\t---Price: %.2f$",
                this.getPrice());

        str.add(agent);
        str.add(apartment);
        str.add(town);
        str.add(price);

        return String.join("\n", str);
    }
}
