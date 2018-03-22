package com.muf.soap3.ws.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class ClientConfig {
	 @Value("${client.default-uri}")
	  private String defaultUri;

	  @Bean
	  Jaxb2Marshaller marshaller() {
	    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	    marshaller.setContextPath("org.tempuri");

	    return marshaller;
	  }

	  @Bean
	  public WebServiceTemplate webServiceTemplate() {
	    WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
	    webServiceTemplate.setMarshaller(marshaller());
	    webServiceTemplate.setUnmarshaller(marshaller());
	    webServiceTemplate.setDefaultUri(defaultUri);
	    return webServiceTemplate;
	  }
}
