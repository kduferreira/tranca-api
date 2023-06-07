package io.github.kduferreira.trancaapi.trancaapi.config.Security;

import io.github.kduferreira.trancaapi.trancaapi.application.form.TokenService;
import io.github.kduferreira.trancaapi.trancaapi.infra.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

// aqui funciona a parte de autorização com token usando ate sistema de perfil que determinado perfil tem autorização para acessar o CRUD
    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private  TokenService tokenService;

    @Autowired
    private  UsuarioRepository usuarioRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //metodo para  configurar na parte de autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //configuracoes de autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/usuario").permitAll()
                .antMatchers(HttpMethod.POST, "/usuario").permitAll()
                .antMatchers(HttpMethod.GET, "/usuario/*").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/condominio").permitAll()
                .antMatchers(HttpMethod.GET, "/perfis").permitAll()
                .antMatchers(HttpMethod.GET, "/condominio/*").hasAnyRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.PUT, "/condominio/*").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.DELETE, "/condominio/*").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.DELETE, "/espaco/*").hasRole("ADMINISTRADOR")
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService,usuarioRepository), UsernamePasswordAuthenticationFilter.class);
    }

    //configuracoes de recursos estaticos(js,css, imagens etc.)
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**","/configuration/**", "/swagger-resources/**");
    }
}
