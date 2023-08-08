package de.woock.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class WebConfig {
	 @Bean(name = "messageSource")
	    public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource() {
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	        messageSource.setBasenames("classpath:messages");
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    }
}
