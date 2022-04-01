package softuni.exam.instagraphlite.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String caption;

    @ManyToOne
    private User user;

    @ManyToOne
    private Picture picture;

    /**
     * Posts
     * •	id – integer number, primary identification field, auto increment.
     * •	caption – a char sequence. Cannot be null. Must be at least 21 characters, inclusive.
     * •	user – a User. Cannot be null.
     * •	picture – a Picture. Cannot be null.
     */

    public Post() {
    }

    public Post(int id, String caption, User user, Picture picture) {
        this.id = id;
        this.caption = caption;
        this.user = user;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
