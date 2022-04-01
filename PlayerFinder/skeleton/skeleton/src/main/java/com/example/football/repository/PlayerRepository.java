package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//ToDo:
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByEmail(String email);

    List<Player> findByBirthDateAfterOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc(LocalDate after);

    @Query("select p from Player p where p.birthDate > '1995-01-01' and p.birthDate < '2003-01-01' " +
            "order by p.stat.shooting desc, p.stat.passing desc, p.stat.endurance desc, p.lastName asc")
    List<Player> findAllByBirthDateBetween();

    /**
     * •	Order Them by Shooting in Desc Order, Then by Passing in Desc Order, Then by Endurance Desc Order and Finally Then by Player Last Name.
     * •	Extract from the database, the first name, last name, position, team name and the name of the stadium of the team.
     * •	Select only players with birth date after 01-01-1995 and before 01-01-2003
     */
}
