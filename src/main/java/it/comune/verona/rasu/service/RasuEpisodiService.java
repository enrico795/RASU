package it.comune.verona.rasu.service;

import it.comune.verona.rasu.domain.RasuEpisodi;

public interface RasuEpisodiService {

    RasuEpisodi findByIdEpisodio(String id);

    void save(RasuEpisodi rasuEpisodi);

}
