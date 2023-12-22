package it.comune.verona.rasu.configuration;

import it.comune.verona.rasu.security.socialLogin.SocialLoginAuthenticationSuccessHandler;
import it.comune.verona.rasu.security.socialLogin.UserServiceOAuth2UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

    @Bean
    @Order(100)
    public SecurityFilterChain securityFilterChain_01(HttpSecurity http, AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {
        return http
                .securityMatcher("/admin/user", "/admin/log")
                .authorizeHttpRequests(
                        authorize -> {
                            authorize.requestMatchers("/admin/**").hasAnyAuthority("ADMIN_READ", "ADMIN_WRITE");
                            authorize.anyRequest().authenticated();
                        }
                )
                .formLogin(withDefaults())
                .oauth2Login(oauth ->
                        oauth.successHandler(authenticationSuccessHandler))
                .logout(LogoutConfigurer::permitAll)
                .build();
    }

    @Bean
    @Order(101)
    public SecurityFilterChain securityFilterChain_04(HttpSecurity http, AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {
        return http
                .securityMatcher("/rasu/referti/**", "/rasu/referto/**", "rasu/referti_storico/**", "rasu/referto_storico/**")
                .authorizeHttpRequests(
                        authorize -> {
                            authorize.requestMatchers("/rasu/referti/**", "/rasu/referto/**",
                                    "rasu/referti_storico/**", "rasu/referto_storico/**").hasAnyAuthority("REF_WRITE", "REF_READ");
                            authorize.anyRequest().authenticated();
                        }
                )
                .formLogin(withDefaults())
                .oauth2Login(oauth ->
                        oauth.successHandler(authenticationSuccessHandler))
                .logout(LogoutConfigurer::permitAll)
                .build();
    }

    @Bean
    @Order(102)
    public SecurityFilterChain securityFilterChain_05(HttpSecurity http, AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {
        return http
                .securityMatcher("/rasu/archivio")
                .authorizeHttpRequests(
                        authorize -> {
                            authorize.requestMatchers("/rasu/archivio/**").hasAnyAuthority("ARCH_WRITE", "ARCH_READ");
                            authorize.anyRequest().authenticated();
                        }
                )
                .formLogin(withDefaults())
                .oauth2Login(oauth ->
                        oauth.successHandler(authenticationSuccessHandler))
                .logout(LogoutConfigurer::permitAll)
                .build();
    }

    @Bean
    @Order(103)
    public SecurityFilterChain securityFilterChain_06(HttpSecurity http, AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {
        return http
                .securityMatcher("/rasu", "/rasu/invio/**")
                .authorizeHttpRequests(
                        authorize -> {
                            authorize.requestMatchers("/rasu/**").hasAnyAuthority("PAZ_WRITE", "PAZ_READ");
                            authorize.anyRequest().authenticated();
                        }
                )
                .formLogin(withDefaults())
                .oauth2Login(oauth ->
                        oauth.successHandler(authenticationSuccessHandler))
                .logout(LogoutConfigurer::permitAll)
                .build();
    }

    @Bean
    @Order(104)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {
        return http
                .securityMatcher("/")
                .authorizeHttpRequests(
                        authorize -> authorize
                                .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .oauth2Login(oauth ->
                        oauth.successHandler(authenticationSuccessHandler))
                .logout(LogoutConfigurer::permitAll)
                .build();
    }

    @Bean
    @Order(105)
    public SecurityFilterChain securityFilterChain_02(HttpSecurity http, AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {
        return http
                .securityMatcher("/error", "/css/**", "/img/**", "/js/**", "/svg/**")
                .authorizeHttpRequests(authConfig -> authConfig.anyRequest().permitAll())
                .formLogin(withDefaults())
                .oauth2Login(oauth ->
                        oauth.successHandler(authenticationSuccessHandler))
                .logout(LogoutConfigurer::permitAll)
                .build();
    }

    @Bean
    @Order(106)
    public SecurityFilterChain securityFilterChain_03(HttpSecurity http, AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {
        return http
                .authorizeHttpRequests(authConfig -> authConfig.anyRequest().denyAll())
                .formLogin(withDefaults())
                .oauth2Login(oauth ->
                        oauth.successHandler(authenticationSuccessHandler))
                .logout(LogoutConfigurer::permitAll)
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(UserServiceOAuth2UserHandler handler) {
        SocialLoginAuthenticationSuccessHandler authenticationSuccessHandler =
                new SocialLoginAuthenticationSuccessHandler();
        authenticationSuccessHandler.setOidcUserHandler(handler);
        return authenticationSuccessHandler;
    }

}
