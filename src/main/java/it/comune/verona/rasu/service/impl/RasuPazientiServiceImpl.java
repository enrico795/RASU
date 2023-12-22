package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.RasuPazienti;
import it.comune.verona.rasu.repository.RasuPazientiRepository;
import it.comune.verona.rasu.service.RasuPazientiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RasuPazientiServiceImpl implements RasuPazientiService {

    private final RasuPazientiRepository repository;

    @Override
    public RasuPazienti findByCodiceFiscale(String codiceFiscale) {
        if(codiceFiscale.isEmpty())
            return null;

        return repository.findByCodiceFiscale(codiceFiscale);
    }

    @Override
    public void save(RasuPazienti pazienti) {
        repository.save(pazienti);
    }
}
