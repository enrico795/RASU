package it.comune.verona.rasu.service;

import it.comune.verona.rasu.domain.Cvrrasureferti;

import java.util.List;

public interface CvrrasurefertiService {

	void addCvrrasu(Cvrrasureferti cvrrasureferti);

	List<Cvrrasureferti> getAllCvrrasureferti();

	List<Cvrrasureferti> getTopNReferti(int rows);

	Cvrrasureferti getRefertoById(int id);

}