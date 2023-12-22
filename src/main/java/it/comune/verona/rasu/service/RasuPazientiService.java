package it.comune.verona.rasu.service;

import it.comune.verona.rasu.domain.RasuPazienti;

public interface RasuPazientiService {

    RasuPazienti findByCodiceFiscale(String codiceFiscale);

    void save(RasuPazienti pazienti);

}
