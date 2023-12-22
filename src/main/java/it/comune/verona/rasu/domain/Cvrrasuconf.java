package it.comune.verona.rasu.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@Table(name = "cvrrasuconf")
@Entity
public class Cvrrasuconf implements Serializable {
	
	@Id
	private String chiave;
	private String valore;
	
}