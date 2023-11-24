package com.cwms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cwms.security.JwtAuthenticationEntryPoint;
import com.cwms.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeRequests(auth -> auth            		
                		.requestMatchers("/auth/**").permitAll()
                		.requestMatchers("/dashboard/**").permitAll()
                		.requestMatchers("/rights").permitAll()
                		.requestMatchers("/gateinout/**").permitAll()
                		 .requestMatchers("/scan/**").permitAll()
                		.requestMatchers("/ShippingDetails/**").permitAll()
                		.requestMatchers("/jar/addJar/**").permitAll()
                		.requestMatchers("/partyLoa/**").permitAll()
                		.requestMatchers("/jar/**").permitAll()
                		.requestMatchers("/forwardout/**").permitAll()
                		.requestMatchers("/forwardin/**").permitAll()
                		.requestMatchers("/defaultparty/**").permitAll()
                		.requestMatchers("/externaluserrights/**").permitAll()
                		.requestMatchers("/externalParty/**").permitAll()
                		.requestMatchers("/represent/**").permitAll()
                		.requestMatchers("/importpc/**").permitAll()
                		.requestMatchers("/importsub/**").permitAll()
                		.requestMatchers("/history/**").permitAll()
                		.requestMatchers("/importmain/**").permitAll()
                		.requestMatchers("/exportsub/**").permitAll()
                		.requestMatchers("/jardetail/**").permitAll()
                		.requestMatchers("/representive/**").permitAll()
                		.requestMatchers("/barcodeGenerater/**").permitAll()
                		.requestMatchers("/externalparty/**").permitAll()
                		.requestMatchers("/api/processnextids/**").permitAll()
                		.requestMatchers("/holiday/**").permitAll()
                		.requestMatchers("/exportpc/**").permitAll()
                		.requestMatchers("/export1/**").permitAll()
                		.requestMatchers("/sbtransactions/**").permitAll()
                		.requestMatchers("/UserCreation/**").permitAll()
                		.requestMatchers("/excelupload/**").permitAll()
                		.requestMatchers("/export/**").permitAll()
                        .requestMatchers("/user/**").permitAll()
                		.requestMatchers("/detention/**").permitAll()
                		.requestMatchers("/detention-history/**").permitAll()
                		.requestMatchers("/parties/**").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/Invoice/**").permitAll()
                        .requestMatchers("/invoicetaxdetails/**").permitAll()
                        .requestMatchers("/importHeavy/**").permitAll()
                        .requestMatchers("/import/**").permitAll()
                        .requestMatchers("/import/tpdate").permitAll()
                        .requestMatchers("/api1/**").permitAll()
                        .requestMatchers("/api2/**").permitAll()
                        .requestMatchers("/Airline/**").permitAll()
                        .requestMatchers("service/**").permitAll()
                        .requestMatchers("cfstarrif/**").permitAll()
                        .requestMatchers("/tarrif/**").permitAll()
                        .requestMatchers("/range/**").permitAll()
                        .requestMatchers("/NewReprentative/**").permitAll()
                        .requestMatchers("/externalParty/**").permitAll()
                        .requestMatchers("/home/**").authenticated()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
