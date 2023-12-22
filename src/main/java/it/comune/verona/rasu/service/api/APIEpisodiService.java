package it.comune.verona.rasu.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import it.comune.verona.rasu.domain.AccertamentiRequest;
import it.comune.verona.rasu.domain.accertamenti.AccertamentiObj;
import it.comune.verona.rasu.domain.accertamenti.NoteObj;
import it.comune.verona.rasu.domain.accertamenti.TipologiaObj;
import it.comune.verona.rasu.domain.episodi.EpisodiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;


@Service
@Slf4j
public class APIEpisodiService {

    private static final String url = "https://toolkit.consorzioarsenal.it/api/8051/rasu/episodiRASU?comuneIncidente=023091";
    private static final String url_post = "https://toolkit.consorzioarsenal.it/api/8051/rasu/RichiestaAccertamenti";

    private final RestTemplate restTemplate;

    @Autowired
    public APIEpisodiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EpisodiResponse[] getAllEpisodi() {
        try {

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(""), String.class);

            if(!response.getStatusCode().equals(HttpStatus.OK))
                throw new ResponseStatusException(
                        response.getStatusCode(),
                        "[RASU_ERROR] Chiamata API fallita!"
                );

            log.info("[RASU_INFO] Chiamata API avvenuta con successo!");

            Gson gson = new Gson();

            return gson.fromJson(response.getBody(), EpisodiResponse[].class);
        } catch (Exception e) {

            log.error("[RASU_ERROR] Qualcosa è andato storto nel download dei dati tramite l'API.", e);

            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "[RASU_ERROR] Si è verificato un problema nella chiamata API",
                    e
            );
        }
    }

    public void sendAccertamentiRequest() {
        try {

            AccertamentiObj accertamenti = new AccertamentiObj();
            accertamenti.setNote(new NoteObj[]{new NoteObj("ncognome", "2023-10-10T10:10:00Z", "")});
            accertamenti.setTipologia(new TipologiaObj[]{new TipologiaObj("sostenze alcoliche", true)});

            AccertamentiRequest accertamentiRequest = new AccertamentiRequest();
            accertamentiRequest.setIdEpisodio("202312101310");
            accertamentiRequest.setUlss("050912");
            accertamentiRequest.setAccertamenti(accertamenti);

            ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String jsonObj = objectWriter.writeValueAsString(accertamentiRequest);

            System.out.println(jsonObj);

            ResponseEntity<String> response = restTemplate.exchange(url_post, HttpMethod.POST, new HttpEntity<>(jsonObj), String.class);

            if(response.getStatusCode().equals(HttpStatus.OK))
                log.info("Chiamata API eseguita con successo!");
        } catch (Exception e) {

            log.error("Qualcosa è andato storo nell'invio dei dati tramite l'API.", e);

            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Si è verificato un problema nella chiamata API",
                    e
            );
        }
    }

}
