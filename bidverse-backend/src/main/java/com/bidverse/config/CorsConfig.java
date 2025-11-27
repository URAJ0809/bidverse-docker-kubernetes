package com.bidverse.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Value("${FRONTEND_ORIGINS:}")
	private String frontendOrigins;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// If FRONTEND_ORIGINS is set, use those explicit origins (safer).
		// Otherwise fall back to allowing origin patterns so in-cluster and
		// NodePort origins are accepted without listing every host/port.
		if (frontendOrigins != null && !frontendOrigins.isBlank()) {
			String[] origins = frontendOrigins.split("\\s*,\\s*");
			registry.addMapping("/**")
				.allowedOrigins(origins)               // explicit origins (required when allowCredentials=true)
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowedHeaders("*")
				.allowCredentials(true)
				.maxAge(3600);
		} else {
			registry.addMapping("/**")
				.allowedOriginPatterns("*")          // allow patterns (supports wildcards)
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowedHeaders("*")
				.allowCredentials(true)
				.maxAge(3600);
		}
	}
}