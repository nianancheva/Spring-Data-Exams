package softuni.exam.instagraphlite.models.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "post")
public class PostDTO implements Serializable {

    @XmlElement
    @NotNull
    @Size(min = 21)
    private String caption;

    @XmlElement
    @NotNull
    private UserUsernameDTO user;

    @XmlElement
    @NotNull
    private PicturePathDTO picture;

    /**
     * <post>
     *       <caption>#everything #ring #faith #insta #infinity #swag #sunglasses #smiley #justdoit #the #sleepless #ocean</caption>
     *
     *       <user>
     *              <username>ScoreAntigarein</username>
     *       </user>
     *
     *       <picture>
     *              <path>src/folders/resources/images/story/blocked/png/1S2el3wJ3v.png</path>
     *       </picture>
     * </post>
     */

    public PostDTO() {
    }

    public PostDTO(String caption, UserUsernameDTO user, PicturePathDTO picture) {
        this.caption = caption;
        this.user = user;
        this.picture = picture;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public UserUsernameDTO getUser() {
        return user;
    }

    public void setUser(UserUsernameDTO user) {
        this.user = user;
    }

    public PicturePathDTO getPicture() {
        return picture;
    }

    public void setPicture(PicturePathDTO picture) {
        this.picture = picture;
    }
}
