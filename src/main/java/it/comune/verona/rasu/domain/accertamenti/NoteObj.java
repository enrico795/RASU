package it.comune.verona.rasu.domain.accertamenti;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteObj {

    private String utente;
    private String data;
    private String nota;

}
