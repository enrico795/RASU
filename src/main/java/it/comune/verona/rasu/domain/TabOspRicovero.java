package it.comune.verona.rasu.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Table(name = "tab_ospricovero")
@Entity
public class TabOspRicovero implements Serializable {

    @Id
    private int id;
    private String nome;

}
