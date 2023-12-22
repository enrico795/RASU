package it.comune.verona.rasu.repository;

import it.comune.verona.rasu.domain.RasuPazienti;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RasuPazientiRepository extends CrudRepository<RasuPazienti, String> {

    @Query(value = "SELECT * FROM rasu_pazienti WHERE codicefiscale = ?1", nativeQuery = true)
    RasuPazienti findByCodiceFiscale(String codiceFiscale);

}
