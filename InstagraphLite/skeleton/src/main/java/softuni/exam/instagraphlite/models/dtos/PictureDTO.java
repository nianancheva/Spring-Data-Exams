package softuni.exam.instagraphlite.models.dtos;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PictureDTO {

    @NotNull
    private String path;

    @NotNull
    @Min(value = 500)
    @Max(value = 60000)
    private double size;

    public PictureDTO() {
    }

    public PictureDTO(String path, double size) {
        this.path = path;
        this.size = size;
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

    /**
     * {
     *       "path": "src/folders/resources/images/profile/blocked/bmp/kjOJjKpKh4.bmp",
     *       "size": 632495.57
     *   }
     */
}
