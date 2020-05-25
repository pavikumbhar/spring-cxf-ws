package com.pavikumbhar.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Covers the Configurations required for WebService Response
 * 
 * @author pavikumbhar
 *
 */
@Configuration
public class WebServiceTemplateConfig {
    
    /** Template for responses */
    @Bean
    public WebServiceTemplate webServicetemplate() {
        return new WebServiceTemplate();
    }
}
