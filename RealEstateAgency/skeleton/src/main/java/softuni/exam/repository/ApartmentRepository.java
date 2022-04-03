package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Town;

import java.util.Optional;

// TODO:
@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    //Optional<Apartment> findByTownAndArea(String town, double area);
    //Optional<Apartment> findByAreaAndTown(double area, Town town);

    Optional<Apartment> findByTownAndArea(Town town, double area);
}
