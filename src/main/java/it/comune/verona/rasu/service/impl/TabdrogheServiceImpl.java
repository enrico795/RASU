package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.Tabdroghe;
import it.comune.verona.rasu.repository.TabdrogheRepository;
import it.comune.verona.rasu.service.TabdrogheService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TabdrogheServiceImpl implements TabdrogheService {

    private final TabdrogheRepository repository;

    @Override
    public void addDroghe(Tabdroghe droga) {
        repository.save(droga);
    }

    @Override
    public List<Tabdroghe> getAllDroghe() {
        return (List<Tabdroghe>) repository.findAll();
    }

}
