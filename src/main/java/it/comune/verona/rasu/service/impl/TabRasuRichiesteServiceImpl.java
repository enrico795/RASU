package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.TabRasuRichieste;
import it.comune.verona.rasu.repository.TabRasuRichiesteRepository;
import it.comune.verona.rasu.service.TabRasuRichiesteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TabRasuRichiesteServiceImpl implements TabRasuRichiesteService {

    private final TabRasuRichiesteRepository repository;

    @Override
    public void addRasuRichieste(TabRasuRichieste rasuRichieste) {
        repository.save(rasuRichieste);
    }

    @Override
    public List<TabRasuRichieste> getAllRasuRichieste() {
        return (List<TabRasuRichieste>) repository.findAll();
    }
}
