package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.PostDTO;
import softuni.exam.instagraphlite.models.dtos.PostsDTO;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.util.Util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;

    JAXBContext context;
    Unmarshaller unmarshaller;
    ModelMapper modelMapper;

    private final Path path = Path.of("src","main", "resources", "files", "posts.xml");

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, PictureRepository pictureRepository) throws JAXBException {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;

        this.context = JAXBContext.newInstance(PostsDTO.class);
        this.unmarshaller = context.createUnmarshaller();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        String file = Files.readString(path);
        return file;
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        PostsDTO postsDTO = (PostsDTO) this.unmarshaller
                .unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<PostDTO> postDTOs = new ArrayList<>();

        postDTOs.addAll(postsDTO.getPosts());

        //for (PostDTO postDTO : postsDTO.getPosts()) {
        //            postDTOs.add(postDTO);
        //        }

        List<String> result = new ArrayList<>();

        for (PostDTO postDTO : postDTOs) {

            if (Util.isValid(postDTO)) {

                Optional<User> optUser = this.userRepository.findByUsername(postDTO.getUser().getUsername());
                Optional<Picture> optPicture = this.pictureRepository.findByPath(postDTO.getPicture().getPath());

                if (optUser.isPresent() && optPicture.isPresent()) {

                    Post post = this.modelMapper.map(postDTO, Post.class);

                    post.setUser(optUser.get());
                    post.setPicture(optPicture.get());

                    this.postRepository.save(post);

                    String msg = String.format("Successfully imported Post, made by %s", post.getUser().getUsername());

                    result.add(msg);

                } else {
                    result.add("Invalid Post");
                }

            } else {
                result.add("Invalid Post");
            }

        }

        return String.join("\n", result);
    }
}
