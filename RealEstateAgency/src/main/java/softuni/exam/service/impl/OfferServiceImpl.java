package softuni.exam.service.impl;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferDTO;
import softuni.exam.models.dto.OffersDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.Util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final AgentRepository agentRepository;
    private final ApartmentRepository apartmentRepository;

    private final JAXBContext context;
    private final Unmarshaller unmarshaller;
    private final ModelMapper modelMapper;

    private Path path = Path.of("src", "main", "resources", "files", "xml", "offers.xml");

    public OfferServiceImpl(OfferRepository offerRepository, AgentRepository agentRepository, ApartmentRepository apartmentRepository) throws JAXBException {
        this.offerRepository = offerRepository;
        this.agentRepository = agentRepository;
        this.apartmentRepository = apartmentRepository;

        this.context = JAXBContext.newInstance(OffersDTO.class);
        this.unmarshaller = context.createUnmarshaller();
        this.modelMapper = new ModelMapper();

        Converter<String, LocalDate> converter = new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
                return LocalDate.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
        };

        this.modelMapper.addConverter(converter);
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        String file = Files.readString(path);
        return file;
    }

    @Override
    public String importOffers() throws IOException, JAXBException {

        OffersDTO offersDTO = (OffersDTO) this.unmarshaller
                .unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<OfferDTO> offerDTOs = new ArrayList<>();
        offerDTOs.addAll(offersDTO.getOffers());

        List<String> result = new ArrayList<>();

        for (OfferDTO offerDTO : offerDTOs) {

            if (Util.isValid(offerDTO)) {

                Optional<Agent> optionalAgent = this.agentRepository.findByFirstName(offerDTO.getAgent().getName());

                if (optionalAgent.isPresent()) {

                    Optional<Apartment> optionalApartment = this.apartmentRepository.findById(offerDTO.getApartment().getId());

                    Offer offer = this.modelMapper.map(offerDTO, Offer.class);

                    offer.setAgent(optionalAgent.get());
                    offer.setApartment(optionalApartment.get());

                    this.offerRepository.save(offer);

                    String msg = String.format("Successfully imported offer %.2f",
                            offerDTO.getPrice());

                    result.add(msg);

                } else {
                    result.add("Invalid offer");
                }

            } else {
                result.add("Invalid offer");
            }

        }

        return String.join("\n", result);
    }

    @Override
    public String exportOffers() {

        List<String> result = new ArrayList<>();

        ApartmentType apartmentType = ApartmentType.three_rooms;

        List<Offer> offers = this.offerRepository.findByApartment_ApartmentTypeOrderByApartment_AreaAndOfferPrice(apartmentType);

        for (Offer offer : offers) {
            result.add(offer.toString());
        }

        return String.join("\n", result);
    }
}
