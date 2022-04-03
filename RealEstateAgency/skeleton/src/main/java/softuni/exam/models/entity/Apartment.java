package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "apartments")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "apartment_type", nullable = false)
    private ApartmentType apartmentType;

    @Column(nullable = false)
    private double area;

    @ManyToOne(optional = false)
    private Town town;

    /**
     * Apartment
     * •	id – accepts integer values, a primary identification field, an auto incremented field.
     * •	apartment type – the enumeration, one of the following – two_rooms, three_rooms, four_rooms
     * •	area – accepts number values that are more than or equal to 40.00.
     * •	Constraint: The apartment table has а relation with the towns table.
     */

    public Apartment() {
    }

    public Apartment(long id, ApartmentType apartmentType, double area, Town town) {
        this.id = id;
        this.apartmentType = apartmentType;
        this.area = area;
        this.town = town;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
