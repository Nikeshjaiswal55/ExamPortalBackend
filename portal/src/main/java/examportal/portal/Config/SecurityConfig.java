package examportal.portal.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Deprecated
public class SecurityConfig {

    private static final String[] public_urls ={
        "/login",
        "api/v1/auth/**",
        "/v3/api-docs",
        "/v2/api-docs",
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/webjars/**"};
    
      @Bean
    public JwtDecoder jwtDecoder() {
        // Replace "{your-jwk-set-uri}" with the actual JWK Set URI provided by Auth0
        return NimbusJwtDecoder.withJwkSetUri("https://dev-rurgln6qgnlgikvb.us.auth0.com/.well-known/jwks.json").build();
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
        authorizeRequests.requestMatchers(public_urls).permitAll().
        requestMatchers("/students").authenticated())

        // .authorizeRequests(authorizeRequests -> authorizeRequests.requestMatchers("/Swagger").permitAll())
        .oauth2ResourceServer(oauth2ResourceServer ->
        oauth2ResourceServer.jwt(jwt -> jwt.decoder(jwtDecoder())));

    return http.build();



        // http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
        // .oauth2ResourceServer(oauth2ResourceServer-> oauth2ResourceServer.jwt(jwt-> jwt.decoder(jwtDecoder())));
        // return http.build();
    }

}

