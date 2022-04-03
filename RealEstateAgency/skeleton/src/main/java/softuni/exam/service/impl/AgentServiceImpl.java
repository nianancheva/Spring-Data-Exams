package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;
import softuni.exam.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;
    private final TownRepository townRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;

    private Path path = Path.of("src", "main", "resources", "files", "json", "agents.json");

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, TownRepository townRepository) {
        this.agentRepository = agentRepository;
        this.townRepository = townRepository;

        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        String file = Files.readString(path);
        return file;
    }

    @Override
    public String importAgents() throws IOException {
        String json = this.readAgentsFromFile();

        AgentDTO[] agentDTOs = this.gson.fromJson(json, AgentDTO[].class);

        List<String> result = new ArrayList<>();

        for (AgentDTO agentDTO : agentDTOs) {

            if (Util.isValid(agentDTO)) {

                Optional<Agent> optionalAgent = this.agentRepository.findByFirstName(agentDTO.getFirstName());

                if (optionalAgent.isEmpty()) {

                    Optional<Town> optionalTown = this.townRepository.findByTownName(agentDTO.getTown());

                    //It was not specified whether the name of the town will always be valid, so it is good to check
                    if (optionalTown.isPresent()) {

                        Agent agent = this.modelMapper.map(agentDTO, Agent.class);

                        agent.setTown(optionalTown.get());

                        this.agentRepository.save(agent);

                        String msg = String.format("Successfully imported agent - %s %s",
                                agent.getFirstName(), agent.getLastName());

                        result.add(msg);

                    } else {
                        result.add("Invalid agent");
                    }

                } else {
                    result.add("Invalid agent");
                }

            } else {
                result.add("Invalid agent");
            }

        }

        return String.join("\n", result);
    }
}
