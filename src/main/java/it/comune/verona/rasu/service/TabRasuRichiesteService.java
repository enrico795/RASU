package it.comune.verona.rasu.service;

import it.comune.verona.rasu.domain.TabRasuRichieste;

import java.util.List;

public interface TabRasuRichiesteService {

    void addRasuRichieste(TabRasuRichieste rasuRichieste);

    List<TabRasuRichieste> getAllRasuRichieste();

}
