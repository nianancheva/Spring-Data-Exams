package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;

public class AgentNameDTO {

    @NotNull
    private String name;

    public AgentNameDTO() {
    }

    public AgentNameDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
