package it.comune.verona.rasu.domain;

import it.comune.verona.rasu.domain.accertamenti.AccertamentiObj;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccertamentiRequest {

    private String idEpisodio;
    private String ulss;
    private AccertamentiObj accertamenti;

}
