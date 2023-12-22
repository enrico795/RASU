package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.Cvrrasu;
import it.comune.verona.rasu.repository.CvrrasuRepository;
import it.comune.verona.rasu.service.CvrrasuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CvrrasuServiceImpl implements CvrrasuService {

	private final CvrrasuRepository repository;
	
	@Override
	public void addCvrrasu(Cvrrasu cvrrasu) {
		repository.save(cvrrasu);
	}
	
	@Override
	public List<Cvrrasu> getAllCvrrasu() {
		return (List<Cvrrasu>) repository.findAll();
	}

	@Override
	public Cvrrasu getRefertoById(int id) {
		return repository.getRefertoById(id);
	}

}