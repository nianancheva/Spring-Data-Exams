package com.example.football.service.impl;

import com.example.football.models.dto.TownDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//ToDo - Implement all methods
@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;

    private final Path path = Path.of("src", "main", "resources", "files", "json", "towns.json");

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;

        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        String file = Files.readString(path);
        return file;
    }

    @Override
    public String importTowns() throws IOException {
        String json = this.readTownsFileContent();

        TownDTO[] townDTOs = this.gson.fromJson(json, TownDTO[].class);

        List<String> result = new ArrayList<>();

        for (TownDTO townDTO : townDTOs) {

            if (Util.isValid(townDTO)) {

                Optional<Town> optTown = this.townRepository.findByName(townDTO.getName());

                if (optTown.isEmpty()) {

                    Town town = this.modelMapper.map(townDTO, Town.class);

                    this.townRepository.save(town);

                    String msg = String.format("Successfully imported Town %s - %d",
                            town.getName(), town.getPopulation());

                    result.add(msg);

                } else {
                    result.add("Invalid Town");
                }

            } else {
                result.add("Invalid Town");
            }

        }

        return String.join("\n", result);
    }
}
