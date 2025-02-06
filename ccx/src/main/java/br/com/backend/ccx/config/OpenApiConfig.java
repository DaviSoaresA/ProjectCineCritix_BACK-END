package br.com.backend.ccx.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Value("${prop.swagger.url}")
    private String url;

    @Bean
    public OpenAPI myOpenAPI(){

        Server server = new Server();
        server.setUrl(url);
        server.setDescription("Servidor de Desenvolvimento");

        License license = new License();
        license.setName("Apache 2.0");
        license.setUrl("http://www.apache.org/licenses/LICENSE-2.0.html");

        Info info = new Info();
        info.setTitle("CCX");
		info.setVersion("0.0.1");
		info.setDescription("CineCritix");
		info.setLicense(license);
		info.termsOfService("http://swagger.io/terms/");

        return new OpenAPI().info(info).servers(List.of(server));
    }

    @Bean
    public CorsFilter corsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addAllowedOrigin("*");
            config.addAllowedHeader("*");
            config.addAllowedMethod("*");

            source.registerCorsConfiguration("/v3/api-docs", config);
            return new CorsFilter(source);
    }
}
