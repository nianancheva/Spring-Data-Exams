package com.example.football.service.impl;

import com.example.football.models.dto.PlayerDTO;
import com.example.football.models.dto.PlayersDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.Util;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//ToDo - Implement all methods
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TownRepository townRepository;
    private final TeamRepository teamRepository;
    private final StatRepository statRepository;

    private final JAXBContext context;
    private final Unmarshaller unmarshaller;
    private final ModelMapper modelMapper;

    private final Path path = Path.of("src", "main", "resources", "files", "xml", "players.xml");

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TownRepository townRepository, TeamRepository teamRepository, StatRepository statRepository) throws JAXBException {
        this.playerRepository = playerRepository;
        this.townRepository = townRepository;
        this.teamRepository = teamRepository;
        this.statRepository = statRepository;

        this.context = JAXBContext.newInstance(PlayersDTO.class);
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
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        String file = Files.readString(path);
        return file;
    }

    @Override
    public String importPlayers() throws FileNotFoundException, JAXBException {

        PlayersDTO playersDTO = (PlayersDTO) this.unmarshaller
                .unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<PlayerDTO> playerDTOs = new ArrayList<>();
        playerDTOs.addAll(playersDTO.getPlayers());

        List<String> result = new ArrayList<>();

        for (PlayerDTO playerDTO : playerDTOs) {

            if (Util.isValid(playerDTO)) {

                Optional<Player> optPlayer = this.playerRepository.findByEmail(playerDTO.getEmail());

                if (optPlayer.isEmpty()) {

                    Optional<Town> optTown = this.townRepository.findByName(playerDTO.getTown().getName());
                    Optional<Team> optTeam = this.teamRepository.findByName(playerDTO.getTeam().getName());
                    Optional<Stat> optStat = this.statRepository.findById(playerDTO.getStat().getId());

                    Player player = this.modelMapper.map(playerDTO, Player.class);

                    player.setTown(optTown.get());
                    player.setTeam(optTeam.get());
                    player.setStat(optStat.get());

                    this.playerRepository.save(player);

                    String msg = String.format("Successfully imported Player %s %s - %s",
                            player.getFirstName(), player.getLastName(), player.getPosition().toString());

                    result.add(msg);

                } else {
                    result.add("Invalid Player");
                }

            } else {
                result.add("Invalid Player");
            }

        }

        return String.join("\n", result);
    }

    @Override
    public String exportBestPlayers() {
        List<String> result = new ArrayList<>();

        LocalDate after = LocalDate.of(1995, 1, 1);

        //List<Player> players = this.playerRepository.findByBirthDateAfterOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc(after);
        List<Player> players = this.playerRepository.findAllByBirthDateBetween();

        for (Player player : players) {
            result.add(player.toString());
        }

        return String.join("\n", result);
    }
}
