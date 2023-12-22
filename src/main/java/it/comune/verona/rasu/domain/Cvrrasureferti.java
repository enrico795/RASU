package it.comune.verona.rasu.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@Table(name = "cvrrasureferti")
@Entity
public class Cvrrasureferti implements Serializable {
	
	@Id
	private int rasu_protocollo;
	private String incidente_prot;
	private LocalDate referto_data;
	private String referto_esito;
	private Integer positiv_alcol;
	private String positiv_droghe;
	private String veicolo_provv;
	private LocalDate veicolo_provv_data;
	private String rilev_reparto;
	private String rilev_agenti;
	private String rilev_avviso;
	private String record_log;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "presidio", referencedColumnName = "presidio")
	@JoinColumn(name = "schedaps", referencedColumnName = "schedaps")
	protected Cvrrasu cvrrasu;

	private String referto_note;
	private String def_rapporto;
	
}