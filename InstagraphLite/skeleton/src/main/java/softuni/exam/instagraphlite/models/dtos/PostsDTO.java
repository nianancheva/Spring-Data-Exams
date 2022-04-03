package softuni.exam.instagraphlite.models.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostsDTO {

    @XmlElement(name = "post")
    private List<PostDTO> posts;

    public PostsDTO() {
    }

    public PostsDTO(List<PostDTO> posts) {
        this.posts = posts;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
}
