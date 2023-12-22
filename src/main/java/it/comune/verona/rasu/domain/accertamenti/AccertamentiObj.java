package it.comune.verona.rasu.domain.accertamenti;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
public class AccertamentiObj {

    private TipologiaObj[] tipologia;
    private NoteObj[] note;

}
