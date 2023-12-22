package it.comune.verona.rasu.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Table(name = "tab_provveicolo")
@Entity
public class TabProvVeicolo implements Serializable {

    @Id
    private int id;
    private String nome;

}
