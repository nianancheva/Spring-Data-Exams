package softuni.exam.models.dto;

import softuni.exam.models.entity.ApartmentType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentDTO implements Serializable {

    @XmlElement
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private ApartmentType apartmentType;

    @XmlElement
    @Min(value = 40)
    @NotNull
    private double area;

    @XmlElement(name = "town")
    @NotNull
    private String townName;

    /**
     * <apartment>
     *         <apartmentType>three_rooms</apartmentType>
     *         <area>53.47</area>
     *         <town>Lille</town>
     *     </apartment>
     */

    public ApartmentDTO() {
    }

    public ApartmentDTO(ApartmentType apartmentType, double area, String town) {
        this.apartmentType = apartmentType;
        this.area = area;
        this.townName = town;
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
