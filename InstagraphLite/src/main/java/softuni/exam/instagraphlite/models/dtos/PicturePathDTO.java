package softuni.exam.instagraphlite.models.dtos;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

public class PicturePathDTO {

    @NotNull
    //@XmlElement
    private String path;

    public PicturePathDTO() {
    }

    public PicturePathDTO(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
