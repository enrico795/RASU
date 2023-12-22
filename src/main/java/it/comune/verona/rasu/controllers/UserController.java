package it.comune.verona.rasu.controllers;

import it.comune.verona.rasu.domain.User;
import it.comune.verona.rasu.security.CustomUserDetails;
import it.comune.verona.rasu.security.socialLogin.CustomOidcUser;
import it.comune.verona.rasu.service.RoleService;
import it.comune.verona.rasu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    UserService userService;
    RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/user")
    public String user(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("page_title", "users");
        model.addAttribute("currentUser", getCurrentUser(authentication));

        return "user";
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
