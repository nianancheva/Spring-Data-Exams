package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.UserDTO;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;

    private Gson gson;
    private ModelMapper modelMapper;

    private final Path path = Path.of("src","main", "resources", "files", "users.json");

    //----------

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PictureRepository pictureRepository) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;

        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        String file = Files.readString(path);
        return file;
    }

    @Override
    public String importUsers() throws IOException {
        UserDTO[] userDTOS = this.gson.fromJson(this.readFromFileContent(), UserDTO[].class);

        List<String> result = new ArrayList<>();

        for (UserDTO userDTO : userDTOS) {

            if (Util.isValid(userDTO)) {

                Optional<User> optUser = this.userRepository.findByUsername(userDTO.getUsername());

                if (optUser.isEmpty()) {

                    Optional<Picture> optPicture = this.pictureRepository.findByPath(userDTO.getProfilePicture());

                    if (optPicture.isPresent()) {

                        User user = this.modelMapper.map(userDTO, User.class);

                        user.setProfilePicture(optPicture.get());

                        this.userRepository.save(user);

                        String msg = String.format("Successfully imported User: %s", user.getUsername());

                        result.add(msg);

                    } else {
                        result.add("Invalid User");
                    }

                } else {
                    result.add("Invalid User");
                }

            } else {
                result.add("Invalid User");
            }

        }

        return String.join("\n", result);
    }

    @Override
    public String exportUsersWithTheirPosts() {
        List<User> users = this.userRepository.findAllUsersWithPostsCount();

        List<String> result = new ArrayList<>();

        for (User user : users) {
            result.add(user.toString());
        }

        return String.join("\n", result);
    }
}
