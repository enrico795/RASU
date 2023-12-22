package it.comune.verona.rasu.repository;

import it.comune.verona.rasu.domain.Cvrrasulog;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CvrrasulogRepository extends CrudRepository<Cvrrasulog, LocalDate> {
	
	@Query(value = "SELECT * FROM cvrrasulog ORDER BY dataLog DESC LIMIT ?1", nativeQuery = true)
    List<Cvrrasulog> getTopNLogs(int rows);
	
}