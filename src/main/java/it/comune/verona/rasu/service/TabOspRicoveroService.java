package it.comune.verona.rasu.service;

import it.comune.verona.rasu.domain.TabOspRicovero;

import java.util.List;

public interface TabOspRicoveroService {

    void addOspRicovero(TabOspRicovero ospRicovero);

    List<TabOspRicovero> getAllOspRicovero();

}
