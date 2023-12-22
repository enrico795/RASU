package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.Cvrrasuconf;
import it.comune.verona.rasu.repository.CvrrasuconfRepository;
import it.comune.verona.rasu.service.CvrrasuconfService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CvrrasuconfServiceImpl implements CvrrasuconfService {

	private final CvrrasuconfRepository repository;
	
	@Override
	public void addCvrrasu(Cvrrasuconf cvrrasuconf) {
		repository.save(cvrrasuconf);
	}
	
	@Override
	public List<Cvrrasuconf> getAllCvrrasuconf() {
		return (List<Cvrrasuconf>) repository.findAll();
	}

}