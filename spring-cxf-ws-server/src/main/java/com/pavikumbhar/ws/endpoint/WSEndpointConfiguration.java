package com.pavikumbhar.ws.endpoint;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pavikumbhar.ws.service.HelloPortImpl;
/**
 * 
 * @author pavikumbhar
 *
 */
@Configuration
public class WSEndpointConfiguration {

	@Autowired
	private Bus bus;

	@Bean
	public Endpoint helloEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, new HelloPortImpl());
		endpoint.publish("/hello");
		return endpoint;
	}

}
