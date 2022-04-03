package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;

public class ApartmentIdDTO {

    @NotNull
    private long id;

    public ApartmentIdDTO() {
    }

    public ApartmentIdDTO(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
