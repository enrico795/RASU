package it.comune.verona.rasu.domain;

import it.comune.verona.rasu.domain.id.CvrrasuId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Table(name = "cvrrasu")
@Entity
@IdClass(CvrrasuId.class)
public class Cvrrasu implements Serializable {

	@Id
	@Column(name = "presidio", updatable = false, nullable = false)
	private String presidio;
	@Id
	@Column(name = "schedaps", updatable = false, nullable = false)
	private String schedaps;
	private String ps_desc;
	private String data_evento;
	private String cognome;
	private String nome;
	private LocalDate data_nascita;
	private String codice_fiscale;
	private String causa_incidente;
	private String luogo_incidente;
	private String comune_incidente;
	private String modalita_arrivo;
	private String modalita_accesso;
	private String rasu_protocollo;
	private LocalDate rasu_datains;
	private LocalDate rasu_datainvio;
	private String rasu_note;
	private String pm_ip;
	private String pm_user;
	private String pm_cognome;
	private String pm_nome;
	private LocalDate pmva_datanascita;
	private String pmva_cognome;
	private String pmva_nome;
	private String pmva_residenza;
	private String pmva_luogoincidente;
	
}