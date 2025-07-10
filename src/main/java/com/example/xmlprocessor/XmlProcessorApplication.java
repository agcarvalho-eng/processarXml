package com.example.xmlprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * A classe principal que serve como ponto de entrada para a aplicação Spring Boot.
 * <p>
 * A anotação {@code @SpringBootApplication} engloba as anotações {@code @Configuration},
 * {@code @EnableAutoConfiguration}, e {@code @ComponentScan}, simplificando a configuração.
 * A anotação {@code @EnableConfigurationProperties} é usada para habilitar o suporte
 * a classes de propriedades de configuração.
 */
@SpringBootApplication
@EnableConfigurationProperties
public class XmlProcessorApplication {

	/**
	 * O método principal que é executado para iniciar a aplicação Spring Boot.
	 *
	 * @param args Argumentos de linha de comando que podem ser passados para a aplicação.
	 */
	public static void main(String[] args) {
		SpringApplication.run(XmlProcessorApplication.class, args);
	}

	/**
	 * Define um bean {@link WebMvcConfigurer} para configurar o CORS (Cross-Origin Resource Sharing)
	 * para toda a aplicação.
	 * <p>
	 * Esta configuração permite que requisições de qualquer origem ("*") acessem os endpoints
	 * da aplicação, utilizando os métodos HTTP especificados (GET, POST, PUT, DELETE, OPTIONS).
	 *
	 * @return uma instância de {@link WebMvcConfigurer} com as políticas de CORS definidas.
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Aplica a todos os endpoints
						.allowedOrigins("*")   // Permite todas as origens
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite métodos específicos
						.allowedHeaders("*");  // Permite todos os cabeçalhos
			}
		};
	}
}