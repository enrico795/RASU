package it.comune.verona.rasu.controllers;

import it.comune.verona.rasu.domain.User;
import it.comune.verona.rasu.security.CustomUserDetails;
import it.comune.verona.rasu.security.socialLogin.CustomOidcUser;
import it.comune.verona.rasu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("page_title", "index");
        model.addAttribute("currentUser", getCurrentUser(authentication));

        return "index";
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

}
