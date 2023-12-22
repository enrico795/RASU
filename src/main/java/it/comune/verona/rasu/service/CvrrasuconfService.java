package it.comune.verona.rasu.service;

import it.comune.verona.rasu.domain.Cvrrasuconf;

import java.util.List;

public interface CvrrasuconfService {

	void addCvrrasu(Cvrrasuconf cvrrasuconf);

	List<Cvrrasuconf> getAllCvrrasuconf();

}