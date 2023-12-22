package it.comune.verona.rasu.service;

import it.comune.verona.rasu.domain.TabRefEsito;

import java.util.List;

public interface TabRefEsitoService {

    void addEsito(TabRefEsito refEsito);

    List<TabRefEsito> getAllEsito();

}
