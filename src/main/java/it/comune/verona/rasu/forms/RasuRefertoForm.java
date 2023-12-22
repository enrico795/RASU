package it.comune.verona.rasu.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class RasuRefertoForm {

    private String incidente_prot;
    private String referto_esito;
    private Float positiv_alcol;
    private String positiv_droghe;
    private String veicolo_provv;
    private LocalDate veicolo_provv_data;
    private String rilev_reparto;
    private String rilev_agenti;
    private String rilev_avviso;
    private String def_rapporto;
    private LocalDate referto_data;
    private String referto_note;

}
