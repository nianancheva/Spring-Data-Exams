package softuni.exam.instagraphlite.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String path;

    @Column(nullable = false)
    private double size;

    public Picture() {
    }

    public Picture(int id, String path, double size) {
        this.id = id;
        this.path = path;
        this.size = size;
    }

    /**
     * Pictures
     * •	id – integer number, primary identification field, auto increment.
     * •	path – a char sequence. Cannot be null. The path is unique.
     * •	size – a floating point number. Cannot be null. Must be between 500 and 60000 (both numbers are INCLUSIVE)
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        String str = String.format("%.2f - %s",
                this.getSize(), this.getPath());

        return str;
    }
}
