package it.comune.verona.rasu.service;

import it.comune.verona.rasu.domain.Cvrrasulog;

import java.util.List;

public interface CvrrasulogService {

	void addCvrrasu(Cvrrasulog cvrrasulog);

	List<Cvrrasulog> getAllCvrrasulog();

	List<Cvrrasulog> getTopNLogs(int rows);

}