package it.comune.verona.rasu.controllers;

import it.comune.verona.rasu.domain.RasuEpisodi;
import it.comune.verona.rasu.domain.RasuPazienti;
import it.comune.verona.rasu.domain.User;
import it.comune.verona.rasu.domain.episodi.EpisodiResponse;
import it.comune.verona.rasu.forms.RasuRefertoForm;
import it.comune.verona.rasu.security.CustomUserDetails;
import it.comune.verona.rasu.security.socialLogin.CustomOidcUser;
import it.comune.verona.rasu.service.*;
import it.comune.verona.rasu.service.api.APIEpisodiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
@Slf4j
public class RasuController {

    UserService userService;
    CvrrasuService cvrrasuService;
    CvrrasurefertiService refertiService;
    TabRefEsitoService tabRefEsitoService;
    TabdrogheService tabdrogheService;
    TabProvVeicoloService tabProvVeicoloService;
    APIEpisodiService episodiService;
    RasuEpisodiService rasuEpisodiService;
    RasuPazientiService rasuPazientiService;

    @Autowired
    public RasuController(UserService userService, CvrrasuService cvrrasuService, CvrrasurefertiService refertiService,
                          TabRefEsitoService tabRefEsitoService, TabdrogheService tabdrogheService,
                          TabProvVeicoloService tabProvVeicoloService, APIEpisodiService episodiService,
                          RasuEpisodiService rasuEpisodiService, RasuPazientiService rasuPazientiService) {
        this.userService = userService;
        this.cvrrasuService = cvrrasuService;
        this.refertiService = refertiService;
        this.tabRefEsitoService = tabRefEsitoService;
        this.tabdrogheService = tabdrogheService;
        this.tabProvVeicoloService = tabProvVeicoloService;
        this.episodiService = episodiService;
        this.rasuEpisodiService = rasuEpisodiService;
        this.rasuPazientiService = rasuPazientiService;
    }

    @Scheduled(cron = "0 */1 * ? * *")
    public void runEveryMinute() {
        EpisodiResponse[] allEpisodi = episodiService.getAllEpisodi();

        for(EpisodiResponse response : allEpisodi) {
            if(rasuEpisodiService.findByIdEpisodio(response.getIdEpisodio()) != null)
                continue;

            if(rasuPazientiService.findByCodiceFiscale(response.getCodiceFiscale()) != null)
                continue;

            RasuPazienti paziente = new RasuPazienti();
            paziente.setCodiceFiscale(response.getCodiceFiscale());
            paziente.setNome(response.getNome());
            paziente.setCognome(response.getCognome());
            paziente.setDataDiNascita(LocalDate.parse(response.getDataDiNascita()));

            rasuPazientiService.save(paziente);

            RasuEpisodi episodio = getRasuEpisodi(response, paziente);

            rasuEpisodiService.save(episodio);

            log.info("[RASU_INFO] Paziente salvato con successo!");
        }

    }

    @GetMapping("/rasu")
    public String rasu(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("page_title", "rasu");
        model.addAttribute("currentUser", getCurrentUser(authentication));
        model.addAttribute("episodi", episodiService.getAllEpisodi());

        return "rasu";
    }

    @GetMapping("/rasu/invio/{id}")
    public String rasuInvio(Model model, @PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("page_title", "rasu");
        model.addAttribute("currentUser", getCurrentUser(authentication));
        model.addAttribute("episodio", rasuEpisodiService.findByIdEpisodio(id));

        return "rasu_invio";
    }

    @GetMapping("/rasu/archivio")
    public String rasu_archivio(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        episodiService.sendAccertamentiRequest();

        model.addAttribute("page_title", "rasuArchivio");
        model.addAttribute("currentUser", getCurrentUser(authentication));
        model.addAttribute("referti", refertiService.getTopNReferti(25));
        return "rasu_archivio";
    }

    @GetMapping("/rasu/referti")
    public String rasuarchivio(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        episodiService.sendAccertamentiRequest();

        model.addAttribute("page_title", "rasuReferti");
        model.addAttribute("currentUser", getCurrentUser(authentication));
        model.addAttribute("referti", refertiService.getTopNReferti(25));
        return "rasureferti";
    }

    @GetMapping("/rasu/referti/{rows}")
    public String rasuNreferti_new(Model model, @PathVariable String rows){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("page_title", "rasuReferti");
        model.addAttribute("currentUser", getCurrentUser(authentication));
        model.addAttribute("referti", refertiService.getTopNReferti(Integer.parseInt(rows)));

        return "rasureferti";
    }

    @GetMapping("/rasu/referto/{id}")
    public String rasureferto(Model model, @PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("refertoId", id);
        model.addAttribute("page_title", "rasuReferti");
        model.addAttribute("currentUser", getCurrentUser(authentication));
        model.addAttribute("referto", refertiService.getRefertoById(Integer.parseInt(id)));
        model.addAttribute("tabEsito", tabRefEsitoService.getAllEsito());
        model.addAttribute("tabDroghe", tabdrogheService.getAllDroghe());
        model.addAttribute("tabProvvVeivolo", tabProvVeicoloService.getAllProvVeicoli());
        model.addAttribute("referto_form", new RasuRefertoForm());

        return "rasureferto";
    }

    @GetMapping("/rasu/referti_storico")
    public String rasureferti(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("page_title", "rasuRefertiStorico");
        model.addAttribute("currentUser", getCurrentUser(authentication));
        model.addAttribute("referti", refertiService.getTopNReferti(25));

        return "rasurefertistorico";
    }

    @GetMapping("/rasu/referti_storico/{rows}")
    public String rasuNreferti(Model model, @PathVariable String rows){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("page_title", "rasuRefertiStorico");
        model.addAttribute("currentUser", getCurrentUser(authentication));
        model.addAttribute("referti", refertiService.getTopNReferti(Integer.parseInt(rows)));

        return "rasurefertistorico";
    }

    @GetMapping("/rasu/referto_storico/{id}")
    public String rasureferto_2(Model model, @PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("page_title", "rasuRefertiStorico");
        model.addAttribute("currentUser", getCurrentUser(authentication));
        model.addAttribute("referto", refertiService.getRefertoById(Integer.parseInt(id)));

        return "rasureferto_storico";
    }

    private User getCurrentUser(Authentication authentication) {
        User currentUser;

        if(authentication.getPrincipal() instanceof CustomOidcUser oidcUser) {
            currentUser = userService.getByUsername(oidcUser.getUsername());
        } else {
            CustomUserDetails oidcUser = (CustomUserDetails) authentication.getPrincipal();
            currentUser = userService.getByUsername(oidcUser.getUsername());
        }

        return currentUser;
    }

    private static RasuEpisodi getRasuEpisodi(EpisodiResponse response, RasuPazienti paziente) {
        DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'ZZ'");
        DateTimeFormatter formatter_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        RasuEpisodi episodio = new RasuEpisodi();
        episodio.setIdEpisodio(response.getIdEpisodio());
        episodio.setUlss(response.getUlss());
        episodio.setIdPresidio(response.getIdPresidio());
        episodio.setDataOraInizioEpisodio(LocalDateTime.parse(response.getDataOraInizioEpisodio(), formatter_1));
        episodio.setCodiceFiscale(paziente);
        episodio.setDataOraIncidente(LocalDateTime.parse(response.getDataOraIncidente(), formatter_2));
        episodio.setLuogoIncidente(response.getLuogoIncidente());
        episodio.setComuneIncidente(response.getComuneIncidente());
        episodio.setModalitaAccesso(response.getModalitaAccesso());
        episodio.setCausaIncidente(response.getCausaIncidente());
        episodio.setModalitaArrivo(response.getModalitaArrivo());
        episodio.setSostanzeAlcoliche(false);
        episodio.setSostanzeStupefacenti(false);

        return episodio;
    }
}
