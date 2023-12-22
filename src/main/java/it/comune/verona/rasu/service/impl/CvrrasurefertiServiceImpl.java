package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.Cvrrasureferti;
import it.comune.verona.rasu.repository.CvrrasurefertiRepository;
import it.comune.verona.rasu.service.CvrrasurefertiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CvrrasurefertiServiceImpl implements CvrrasurefertiService {

	private final CvrrasurefertiRepository repository;

	@Override
	public void addCvrrasu(Cvrrasureferti cvrrasureferti) {
		repository.save(cvrrasureferti);
	}

	@Override
	public List<Cvrrasureferti> getAllCvrrasureferti() {
		return (List<Cvrrasureferti>) repository.findAll();
	}

	@Override
	public List<Cvrrasureferti> getTopNReferti(int rows) {
		return repository.getTopNReferti(rows);
	}

	@Override
	public Cvrrasureferti getRefertoById(int id) {
		return repository.getRefertoById(id);
	}

}