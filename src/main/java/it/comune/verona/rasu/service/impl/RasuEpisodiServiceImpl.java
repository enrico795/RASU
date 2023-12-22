package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.RasuEpisodi;
import it.comune.verona.rasu.repository.RasuEpisodiRepository;
import it.comune.verona.rasu.service.RasuEpisodiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RasuEpisodiServiceImpl implements RasuEpisodiService {

    private final RasuEpisodiRepository repository;

    @Override
    public RasuEpisodi findByIdEpisodio(String id) {
        if (id.isEmpty())
            return null;

        return repository.findByIdEpisodio(id);
    }

    @Override
    public void save(RasuEpisodi rasuEpisodi) {
        repository.save(rasuEpisodi);
    }

}
