package it.comune.verona.rasu.repository;

import it.comune.verona.rasu.domain.RasuEpisodi;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RasuEpisodiRepository extends CrudRepository<RasuEpisodi, Integer> {

    @Query(value = "SELECT * FROM rasu_episodi WHERE idepisodio = ?1", nativeQuery = true)
    RasuEpisodi findByIdEpisodio(String idepisodio);

}
