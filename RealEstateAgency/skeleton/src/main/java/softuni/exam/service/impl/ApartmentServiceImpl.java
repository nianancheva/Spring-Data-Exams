package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ApartmentDTO;
import softuni.exam.models.dto.ApartmentsDTO;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.util.Util;

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
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final TownRepository townRepository;

    private final JAXBContext context;
    private final Unmarshaller unmarshaller;
    private final ModelMapper modelMapper;

    private Path path = Path.of("src", "main", "resources", "files", "xml", "apartments.xml");

    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownRepository townRepository) throws JAXBException {
        this.apartmentRepository = apartmentRepository;
        this.townRepository = townRepository;

        this.context = JAXBContext.newInstance(ApartmentsDTO.class);
        this.unmarshaller = context.createUnmarshaller();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        String file = Files.readString(path);
        return file;
    }

    @Override
    public String importApartments() throws IOException, JAXBException {

        ApartmentsDTO apartmentsDTO = (ApartmentsDTO) this.unmarshaller
                .unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<ApartmentDTO> apartmentDTOs = new ArrayList<>();
        apartmentDTOs.addAll(apartmentsDTO.getApartments());

        List<String> result = new ArrayList<>();

        for (ApartmentDTO apartmentDTO : apartmentDTOs) {

            if (Util.isValid(apartmentDTO)) {

                Optional<Town> optionalTown = this.townRepository.findByTownName(apartmentDTO.getTownName());

//                Optional<Apartment> optionalApartment = this.apartmentRepository.findByAreaAndTown(
//                        apartmentDTO.getArea(), optionalTown.get());

                Optional<Apartment> optionalApartment = this.apartmentRepository.findByTownAndArea(
                        optionalTown.get(), apartmentDTO.getArea());

                if (optionalApartment.isEmpty()) {

                    //Optional<Town> optionalTown = this.townRepository.findByTownName(apartmentDTO.getTownName());

                    Apartment apartment = this.modelMapper.map(apartmentDTO, Apartment.class);

                    apartment.setTown(optionalTown.get());

                    this.apartmentRepository.save(apartment);

                    String msg = String.format("Successfully imported apartment %s - %.2f",
                            apartment.getApartmentType().toString(), apartment.getArea());

                    result.add(msg);

                } else {
                    result.add("Invalid apartment");
                }

            } else {
                result.add("Invalid apartment");
            }

        }

        return String.join("\n", result);
    }
}
