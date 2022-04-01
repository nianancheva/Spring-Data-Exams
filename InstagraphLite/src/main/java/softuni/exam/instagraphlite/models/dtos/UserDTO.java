package softuni.exam.instagraphlite.models.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDTO {

    @Size(min = 2, max = 18)
    @NotNull
    private String username;

    @Size(min = 4)
    @NotNull
    private String password;

    @NotNull
    private String profilePicture;

    /**
     * {
     *       "username": "UnderSinduxrein",
     *       "password": "4l8nYGTKMW",
     *       "profilePicture": "InvalidPicturePath"
     *   }
     */

    public UserDTO() {
    }

    public UserDTO(String username, String password, String profilePicture) {
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
