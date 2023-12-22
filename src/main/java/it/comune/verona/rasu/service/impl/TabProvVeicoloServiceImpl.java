package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.TabProvVeicolo;
import it.comune.verona.rasu.repository.TabProvVeicoloRepository;
import it.comune.verona.rasu.service.TabProvVeicoloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TabProvVeicoloServiceImpl implements TabProvVeicoloService {

    private final TabProvVeicoloRepository repository;

    @Override
    public void addProvVeicolo(TabProvVeicolo provVeicolo) {
        repository.save(provVeicolo);
    }

    public List<TabProvVeicolo> getAllProvVeicoli() {
        return (List<TabProvVeicolo>) repository.findAll();
    }

}
