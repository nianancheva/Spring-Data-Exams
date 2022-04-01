package exam.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "laptops")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "mac_address", nullable = false, unique = true)
    private String macAddress;

    @Column(name = "cpu_speed", nullable = false)
    private double cpuSpeed;

    @Column(nullable = false)
    private int ram;

    @Column(nullable = false)
    private int storage;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private WarrantyType warranty;

    @ManyToOne(optional = false)
    private Shop shop;

    /**
     * Laptop
     * •id – accepts integer values, a primary identification field, an auto incremented field.
     * •mac address – accepts char sequences as values where their character length value higher than 8.
     *          The values are unique in the database.
     * •cpu speed – accepts positive floating-point numbers.
     * •ram – accepts number values that are more than or equal to 8 and less than or equal to 128
     * •storage – accepts number values that are more than or equal to 128 and less than or equal to 1024
     * •description – a long and detailed description of all known places with a character length value higher than or equal to 10.
     * •price – accepts a positive number.
     * •warranty type – the enumeration, one of the following – BASIC, PREMIUM, LIFETIME.
     * •	    Constraint: The laptops table has a relation with the shops table.
     */

    public Laptop() {
    }

    public Laptop(long id, String macAddress, double cpuSpeed, int ram, int storage, String description, BigDecimal price, WarrantyType warranty, Shop shop) {
        this.id = id;
        this.macAddress = macAddress;
        this.cpuSpeed = cpuSpeed;
        this.ram = ram;
        this.storage = storage;
        this.description = description;
        this.price = price;
        this.warranty = warranty;
        this.shop = shop;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public WarrantyType getWarranty() {
        return warranty;
    }

    public void setWarranty(WarrantyType warranty) {
        this.warranty = warranty;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
