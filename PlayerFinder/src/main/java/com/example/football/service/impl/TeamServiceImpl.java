package com.example.football.service.impl;

import com.example.football.models.dto.TeamDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
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
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TownRepository townRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;

    private final Path path = Path.of("src", "main", "resources", "files", "json", "teams.json");

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;

        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        String file = Files.readString(path);
        return file;
    }

    @Override
    public String importTeams() throws IOException {
        String json = this.readTeamsFileContent();

        TeamDTO[] teamDTOs = this.gson.fromJson(json, TeamDTO[].class);

        List<String> result = new ArrayList<>();

        for (TeamDTO teamDTO : teamDTOs) {

            if (Util.isValid(teamDTO)) {

                Optional<Team> optTeam = this.teamRepository.findByName(teamDTO.getName());

                if (optTeam.isEmpty()) {

                    Team team = this.modelMapper.map(teamDTO, Team.class);

                    Optional<Town> optionalTown = this.townRepository.findByName(teamDTO.getTownName());
                    team.setTown(optionalTown.get());

                    this.teamRepository.save(team);

                    String msg = String.format("Successfully imported Team %s - %d",
                                team.getName(), team.getFanBase());

                    result.add(msg);

                } else {
                    result.add("Invalid Team");
                }

            } else {
                result.add("Invalid Team");
            }

        }

        return String.join("\n", result);
    }
}
