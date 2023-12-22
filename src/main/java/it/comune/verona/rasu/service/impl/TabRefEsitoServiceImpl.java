package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.TabRefEsito;
import it.comune.verona.rasu.repository.TabRefEsitoRepository;
import it.comune.verona.rasu.service.TabRefEsitoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TabRefEsitoServiceImpl implements TabRefEsitoService {

    private final TabRefEsitoRepository repository;

    @Override
    public void addEsito(TabRefEsito refEsito) {
        repository.save(refEsito);
    }

    @Override
    public List<TabRefEsito> getAllEsito() {
        return (List<TabRefEsito>) repository.findAll();
    }

}
