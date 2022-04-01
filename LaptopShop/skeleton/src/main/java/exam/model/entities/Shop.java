package exam.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private BigDecimal income;

    @Column(nullable = false)
    private String address;

    @Column(name = "employee_count", nullable = false)
    private int employeeCount;

    @Column(name = "shop_area", nullable = false)
    private int shopArea;

    @ManyToOne(optional = false)
    private Town town;

    /**
     * Shop
     * •	id – accepts integer values, a primary identification field, an auto incremented field.
     * •	name – accepts char sequences as values where their character length value higher than or equal to 4. The values are unique in the database.
     * •	income – accepts number values that are more than or equal to 20000.
     * •	address – accepts char sequences as values where their character length value higher than or equal to 4.
     * •	employee count – accepts number values that are between 1 and 50
     * o	(Larger than or equal to 1 and less than or equal to 50).
     * •	shop area – accepts number values that are more than or equal to 150.
     * •	Constraint: The shops table has a relation with the towns table.
     */

    public Shop() {
    }

    public Shop(long id, String name, BigDecimal income, String address, int employeeCount, int shopArea, Town town) {
        this.id = id;
        this.name = name;
        this.income = income;
        this.address = address;
        this.employeeCount = employeeCount;
        this.shopArea = shopArea;
        this.town = town;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public int getShopArea() {
        return shopArea;
    }

    public void setShopArea(int shopArea) {
        this.shopArea = shopArea;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
