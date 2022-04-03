package softuni.exam.instagraphlite.models.dtos;

import javax.validation.constraints.NotNull;

public class UserUsernameDTO {

    @NotNull
    private String username;

    public UserUsernameDTO() {
    }

    public UserUsernameDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
