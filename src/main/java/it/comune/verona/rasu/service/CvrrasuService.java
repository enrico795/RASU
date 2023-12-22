package it.comune.verona.rasu.service;

import it.comune.verona.rasu.domain.Cvrrasu;
import it.comune.verona.rasu.domain.Cvrrasureferti;

import java.time.LocalDateTime;
import java.util.List;

public interface CvrrasuService {

	void addCvrrasu(Cvrrasu cvrrasu);

	List<Cvrrasu> getAllCvrrasu();

	Cvrrasu getRefertoById(int id);

}