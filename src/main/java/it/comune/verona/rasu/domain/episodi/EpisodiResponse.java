package it.comune.verona.rasu.domain.episodi;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.comune.verona.rasu.domain.accertamenti.AccertamentiObj;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EpisodiResponse {

    @JsonProperty("idEpisodio")
    private String idEpisodio;

    @JsonProperty("ulss")
    private String ulss;

    @JsonProperty("idPresidio")
    private String idPresidio;

    @JsonProperty("dataOraInizioEpisodio")
    private String dataOraInizioEpisodio;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("cognome")
    private String cognome;

    @JsonProperty("dataDiNascita")
    private String dataDiNascita;

    @JsonProperty("codiceFiscale")
    private String codiceFiscale;

    @JsonProperty("dataOraIncidente")
    private String dataOraIncidente;

    @JsonProperty("luogoIncidente")
    private String luogoIncidente;

    @JsonProperty("comuneIncidente")
    private String comuneIncidente;

    @JsonProperty("modalitaAccesso")
    private String modalitaAccesso;

    @JsonProperty("causaIncidente")
    private String causaIncidente;

    @JsonProperty("modalitaArrivo")
    private String modalitaArrivo;

    @JsonProperty("accertamentiObj")
    private AccertamentiObj accertamentiObj;

}
