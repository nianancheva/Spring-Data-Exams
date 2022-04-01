package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dtos.PictureDTO;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.DTOValidator;
import softuni.exam.instagraphlite.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    private Gson gson;
    private ModelMapper modelMapper;

    private final Path path = Path.of("src","main", "resources", "files", "pictures.json");

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;

        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        String file = Files.readString(path);
        return file;
    }

    @Override
    public String importPictures() throws IOException {

        PictureDTO[] pictureDTOs = this.gson.fromJson(this.readFromFileContent(), PictureDTO[].class);

        List<String> result = new ArrayList<>();

        for (PictureDTO pictureDTO : pictureDTOs) {

            if (Util.isValid(pictureDTO)) {
                Optional<Picture> optPicture = this.pictureRepository.findByPath(pictureDTO.getPath());

                if (optPicture.isPresent()) {
                    result.add("Invalid Picture");
                } else {
                    Picture picture = this.modelMapper.map(pictureDTO, Picture.class);

                    this.pictureRepository.save(picture);

                    String msg = String.format("Successfully imported Picture, with size %.2f", picture.getSize());

                    result.add(msg);
                }

            } else {
                result.add("Invalid Picture");
            }

        }

        return String.join("\n", result);
    }

    @Override
    public String exportPictures() {

        double size = 30000;

        List<Picture> pictures = this.pictureRepository.findBySizeGreaterThanOrderBySizeAsc(size);

        List<String> result = new ArrayList<>();

        for (Picture picture : pictures) {
            result.add(picture.toString());
        }

        return String.join("\n", result);
    }
}
