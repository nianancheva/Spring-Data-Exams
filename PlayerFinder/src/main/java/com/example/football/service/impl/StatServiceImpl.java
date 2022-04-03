package com.example.football.service.impl;

import com.example.football.models.dto.StatDTO;
import com.example.football.models.dto.StatsDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.Util;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//ToDo - Implement all methods
@Service
public class StatServiceImpl implements StatService {

    private final StatRepository statRepository;

    private final JAXBContext context;
    private final Unmarshaller unmarshaller;
    private final ModelMapper modelMapper;

    private final Path path = Path.of("src", "main", "resources", "files", "xml", "stats.xml");

    @Autowired
    public StatServiceImpl(StatRepository statRepository) throws JAXBException {
        this.statRepository = statRepository;

        this.context = JAXBContext.newInstance(StatsDTO.class);
        this.unmarshaller = context.createUnmarshaller();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        String file = Files.readString(path);
        return file;
    }

    @Override
    public String importStats() throws FileNotFoundException, JAXBException {

        StatsDTO statsDTO = (StatsDTO) this.unmarshaller
                .unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<StatDTO> statDTOs = new ArrayList<>();
        statDTOs.addAll(statsDTO.getStats());

        List<String> result = new ArrayList<>();

        for (StatDTO statDTO : statDTOs) {

            if (Util.isValid(statDTO)) {

                Optional<Stat> optStat = this.statRepository.findByPassingAndShootingAndEndurance(
                        statDTO.getShooting(),
                        statDTO.getPassing(),
                        statDTO.getEndurance());

                if (optStat.isEmpty()) {

                    Stat stat = this.modelMapper.map(statDTO, Stat.class);

                    this.statRepository.save(stat);

                    String msg = String.format("Successfully imported Stat %.2f - %.2f - %.2f",
                            stat.getShooting(),
                            stat.getPassing(),
                            stat.getEndurance());

                    result.add(msg);

                } else {
                    result.add("Invalid Stat");
                }

            } else {
                result.add("Invalid Stat");
            }

        }

        return String.join("\n", result);
    }
}
