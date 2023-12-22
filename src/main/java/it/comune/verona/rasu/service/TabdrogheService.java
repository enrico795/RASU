package it.comune.verona.rasu.service;

import it.comune.verona.rasu.domain.Tabdroghe;

import java.util.List;

public interface TabdrogheService {

    void addDroghe(Tabdroghe droga);

    List<Tabdroghe> getAllDroghe();

}
