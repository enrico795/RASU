package it.comune.verona.rasu.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@Table(name = "cvrrasulog")
@Entity
public class Cvrrasulog implements Serializable {

	@Id
	private LocalDate datalog;
	private String pm_ip;
	private String pm_user;
	private String presidio;
	private String schedaps;
	private String testolog;
	
}