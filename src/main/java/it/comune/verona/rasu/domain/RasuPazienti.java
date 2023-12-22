package it.comune.verona.rasu.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Table(name = "rasu_pazienti")
@Entity
public class RasuPazienti implements Serializable {

    @Id
    @Column(name = "codicefiscale")
    private String codiceFiscale;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "datadinascita")
    private LocalDate dataDiNascita;

}
