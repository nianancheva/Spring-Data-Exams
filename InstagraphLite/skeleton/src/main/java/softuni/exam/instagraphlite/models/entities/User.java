package softuni.exam.instagraphlite.models.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "profile_picture_id")
    private Picture profilePicture;

    @OneToMany(targetEntity = Post.class, mappedBy = "user")
    private Set<Post> posts;

    public User() {
    }

    /**
     * Users
     * •	id – integer number, primary identification field, auto increment.
     * •	username – a char sequence. Cannot be null. The username is unique. Must be between 2 and 18 (both numbers are INCLUSIVE)
     * •	password – a char sequence. Cannot be null. Must be at least 4 characters long, inclusive.
     * •	profilePicture – a Picture. Cannot be null.
     */

    /**
     * One Picture may have many Users, but one User may have only one Picture.
     * One User may have many Posts, but one Post may be from only one User.
     * One Post may have only one Picture, but one Picture can be in many Posts.
     */


    public User(int id, String username, String password, Picture profilePicture, Set<Post> posts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
        this.posts = posts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    /**
     User: ScoreAntigarein
     Post count: 6
     ==Post Details:
     ----Caption: #everything #ring #faith #insta #infinity #swag #sunglasses #smiley #justdoit #the #sleepless #ocean
     ----Picture Size: 10960.40
     ==Post Details:
     ----Caption: #insta #reason #sunglasses #swag #justdoit #what #dusk #morning #madness
     ----Picture Size: 12210.73
     …
     */

    @Override
    public String toString() {
        List<String> str = new ArrayList<>();

        String username = String.format("User: %s", this.getUsername());
        str.add(username);

        String postCount = String.format("Post count: %d", this.getPosts().size());
        str.add(postCount);

        for (Post post : this.getPosts()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("==Post Details:%n"));
            stringBuilder.append(String.format("----Caption: %s%n", post.getCaption()));
            stringBuilder.append(String.format("----Picture Size: %.2f", this.getProfilePicture().getSize()));

            str.add(stringBuilder.toString());
        }

        return String.join("\n", str);
    }
}
