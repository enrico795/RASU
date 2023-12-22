package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.TabOspRicovero;
import it.comune.verona.rasu.repository.TabOspRicoveroRepository;
import it.comune.verona.rasu.service.TabOspRicoveroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TabOspRicoveroServiceImpl implements TabOspRicoveroService {

    private final TabOspRicoveroRepository repository;

    @Override
    public void addOspRicovero(TabOspRicovero ospRicovero) {
        repository.save(ospRicovero);
    }

    @Override
    public List<TabOspRicovero> getAllOspRicovero() {
        return (List<TabOspRicovero>) repository.findAll();
    }
}
