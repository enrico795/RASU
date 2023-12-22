package it.comune.verona.rasu.repository;

import it.comune.verona.rasu.domain.Cvrrasulog;
import it.comune.verona.rasu.domain.Cvrrasureferti;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CvrrasurefertiRepository extends CrudRepository<Cvrrasureferti, Integer> {

    @Query(value = "SELECT * FROM cvrrasureferti ORDER BY rasu_protocollo DESC LIMIT ?1", nativeQuery = true)
    List<Cvrrasureferti> getTopNReferti(int rows);

    @Query(value = "SELECT * FROM cvrrasureferti WHERE rasu_protocollo = ?1", nativeQuery = true)
    Cvrrasureferti getRefertoById(int id);

}