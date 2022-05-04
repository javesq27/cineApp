package com.cine.cine.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select cuenta, pwd, activo from Usuarios where cuenta = ?")
        .authoritiesByUsernameQuery("select cuenta, perfil from Perfiles where cuenta = ?");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        // Los recursos estáticos no requieren autenticación
        .antMatchers(
        "/bootstrap/**", 
        "/images/**",
        "/tinymce/**",
        "/logos/**").permitAll()
        // Las vistas públicas no requieren autenticación
        .antMatchers("/", 
        "/login",
        "/signup",
        "/search",
        "/bcrypt/**",
        "/about").permitAll()

        // Asignar permisos a URLs por ROLES
        
        .antMatchers("/peliculas/**").hasAnyAuthority("EDITOR","GERENTE")
        .antMatchers("/horarios/**").hasAnyAuthority("EDITOR","GERENTE")
        .antMatchers("/noticias/**").hasAnyAuthority("EDITOR","GERENTE") 
        .antMatchers("/banners/**").hasAnyAuthority("GERENTE")
        
        // Todas las demás URLs de la Aplicación requieren autenticación
        .anyRequest().authenticated()
        // El formulario de Login no requiere autenticacion
        .and().formLogin().loginPage("/login").permitAll()
        .and().logout().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    }


    
}