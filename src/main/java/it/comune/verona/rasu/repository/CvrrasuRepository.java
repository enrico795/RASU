package it.comune.verona.rasu.repository;

import it.comune.verona.rasu.domain.Cvrrasu;
import it.comune.verona.rasu.domain.Cvrrasureferti;
import it.comune.verona.rasu.domain.id.CvrrasuId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CvrrasuRepository extends CrudRepository<Cvrrasu, CvrrasuId> {

    @Query(value = "SELECT * FROM cvrrasu WHERE rasu_protocollo = ?1", nativeQuery = true)
    Cvrrasu getRefertoById(int id);

}