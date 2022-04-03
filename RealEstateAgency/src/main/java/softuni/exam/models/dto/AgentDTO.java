package softuni.exam.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AgentDTO implements Serializable {

    @Size(min = 2)
    @NotNull
    private String firstName;

    @Size(min = 2)
    @NotNull
    private String lastName;

    @NotNull
    private String town;

    @Email
    @NotNull
    private String email;

    /**
     * {
     *     "firstName": "Rodrique",
     *     "lastName": "Scoffham",
     *     "town": "Skopje",
     *     "email": "nbattman0@reverbnation.com"
     *   }
     */

    public AgentDTO() {
    }

    public AgentDTO(String firstName, String lastName, String town, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.town = town;
        this.email = email;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
