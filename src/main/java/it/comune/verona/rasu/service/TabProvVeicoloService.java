package it.comune.verona.rasu.service;

import it.comune.verona.rasu.domain.TabProvVeicolo;

import java.util.List;

public interface TabProvVeicoloService {

    void addProvVeicolo(TabProvVeicolo provVeicolo);

    List<TabProvVeicolo> getAllProvVeicoli();

}
