package examportal.portal.Config;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@Deprecated
@CrossOrigin(origins = "*")
public class SecurityConfig {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    private static final String[] public_urls = {
            "/login",
            "api/v1/auth/**",
            "/v3/api-docs",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**" };

    @Bean
    public JwtDecoder jwtDecoder() {
        log.info("SecurityConfig, jwtDecoder Method Start");
        // Replace "{your-jwk-set-uri}" with the actual JWK Set URI provided by Auth0
        log.info("SecurityConfig, jwtDecoder Method Ends");
        return NimbusJwtDecoder.withJwkSetUri("https://dev-mp3ifwfcpsy5t3ok.us.auth0.com/.well-known/jwks.json")
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("SecurityConfig , filterChain Method Start ");

        http.authorizeRequests(authorizeRequests -> authorizeRequests.requestMatchers(public_urls).permitAll()
                .requestMatchers("/student").authenticated())
                // .authorizeRequests(authorizeRequests ->
                // authorizeRequests.requestMatchers("/Swagger").permitAll())
                .oauth2ResourceServer(
                        oauth2ResourceServer -> oauth2ResourceServer.jwt(jwt -> jwt.decoder(jwtDecoder())));

        log.info("SecurityConfig , filterChain Method Ends");
        return http.build();

    }

}
