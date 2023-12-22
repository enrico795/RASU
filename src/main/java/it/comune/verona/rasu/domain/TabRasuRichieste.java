package it.comune.verona.rasu.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Table(name = "tab_rasurichieste")
@Entity
public class TabRasuRichieste implements Serializable {

    @Id
    private int id;
    private String nome;
    private String descrizione;

}
