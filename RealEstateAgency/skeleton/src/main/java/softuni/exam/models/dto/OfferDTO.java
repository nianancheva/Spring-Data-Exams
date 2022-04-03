package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferDTO implements Serializable {

    @XmlElement
    @NotNull
    @Positive
    private BigDecimal price;

    @XmlElement
    @NotNull
    private AgentNameDTO agent;

    @XmlElement
    @NotNull
    private ApartmentIdDTO apartment;

    @XmlElement
    @NotNull
    //format: "dd/MM/yyyy"
    private String publishedOn;

    /**
     * <offer>
     *         <price>206934.00</price>
     *         <agent>
     *             <name>Dotti</name>
     *         </agent>
     *         <apartment>
     *             <id>56</id>
     *         </apartment>
     *         <publishedOn>28/12/2005</publishedOn>
     *     </offer>
     */

    public OfferDTO() {
    }

    public OfferDTO(BigDecimal price, AgentNameDTO agent, ApartmentIdDTO apartment, String publishedOn) {
        this.price = price;
        this.agent = agent;
        this.apartment = apartment;
        this.publishedOn = publishedOn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public AgentNameDTO getAgent() {
        return agent;
    }

    public void setAgent(AgentNameDTO agent) {
        this.agent = agent;
    }

    public ApartmentIdDTO getApartment() {
        return apartment;
    }

    public void setApartment(ApartmentIdDTO apartment) {
        this.apartment = apartment;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }
}
