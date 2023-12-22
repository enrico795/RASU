package it.comune.verona.rasu.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "rasu_episodi")
@Entity
public class RasuEpisodi implements Serializable {

    @Id
    @Column(name = "idepisodio")
    private String idEpisodio;

    @Column(name = "ulss")
    private String ulss;

    @Column(name = "idpresidio")
    private String idPresidio;

    @Column(name = "dataorainizioepisodio")
    private LocalDateTime dataOraInizioEpisodio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codicefiscale", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RasuPazienti codiceFiscale;

    @Column(name = "dataoraincidente")
    private LocalDateTime dataOraIncidente;

    @Column(name = "luogoincidente")
    private String luogoIncidente;

    @Column(name = "comuneincidente")
    private String comuneIncidente;

    @Column(name = "modalitaaccesso")
    private String modalitaAccesso;

    @Column(name = "causaincidente")
    private String causaIncidente;

    @Column(name = "modalitaarrivo")
    private String modalitaArrivo;

    @Column(name = "sostanzealcoliche")
    private boolean sostanzeAlcoliche;

    @Column(name = "sostanzestupefacenti")
    private boolean sostanzeStupefacenti;

}
