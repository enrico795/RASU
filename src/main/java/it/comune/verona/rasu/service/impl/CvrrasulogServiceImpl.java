package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.Cvrrasulog;
import it.comune.verona.rasu.repository.CvrrasulogRepository;
import it.comune.verona.rasu.service.CvrrasulogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CvrrasulogServiceImpl implements CvrrasulogService {

	private final CvrrasulogRepository repository;
	
	@Override
	public void addCvrrasu(Cvrrasulog cvrrasulog) {
		repository.save(cvrrasulog);
	}
	
	@Override
	public List<Cvrrasulog> getAllCvrrasulog() {
		return (List<Cvrrasulog>) repository.findAll();
	}

	@Override
	public List<Cvrrasulog> getTopNLogs(int rows) {
		return repository.getTopNLogs(rows);
	}

}