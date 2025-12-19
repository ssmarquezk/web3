package web3.web3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permitir acceso desde Angular/Ionic (desarrollo y producción)
        configuration.setAllowedOriginPatterns(Arrays.asList(
            "http://localhost:4200", // Puerto por defecto de Angular
            "http://localhost:*",    // Otros puertos locales (Ionic)
            "https://*.vercel.app",  // Ejemplo para Vercel
            "https://*.netlify.app", // Ejemplo para Netlify
            "https://*.github.io",   // GitHub Pages
            "capacitor://localhost", // Ionic Capacitor
            "ionic://localhost",     // Ionic Live Reload
            "http://localhost:8100", // Puerto típico de Ionic
            "http://localhost:3000", // Otro puerto común
            "http://localhost:8080"  // Otro puerto común
        ));
        
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
