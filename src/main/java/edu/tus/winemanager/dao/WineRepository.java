package edu.tus.winemanager.dao;



import edu.tus.winemanager.dto.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tus.winemanager.dto.Wine;

import java.util.List;


@Repository
public interface WineRepository extends JpaRepository<Wine, Long> {
    Wine findFirstByCountry(String country);
    List<Wine> findByCountry(String country);
    List<Wine> findTop5ByCountry(String country);
    List<Wine> findByCountryAndRating(String country, Rating rating);
}
