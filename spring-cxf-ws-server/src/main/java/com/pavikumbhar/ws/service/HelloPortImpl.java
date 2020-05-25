package com.pavikumbhar.ws.service;

import javax.jws.WebService;

import lombok.extern.slf4j.Slf4j;

@WebService(serviceName = "HelloService", portName = "HelloPort", 
targetNamespace = "http://pavikumbhar.ws/", 
endpointInterface = "com.pavikumbhar.ws.service.Hello")
@Slf4j
public class HelloPortImpl implements Hello {
    
    @Override
    public String sayHello(String myname) {
        log.info("Executing operation sayHello" + myname);
        try {
            return "Hello, Welcome to CXF Spring boot " + myname + "!!!";
            
        } catch (Exception ex) {
            log.debug("Exception {}", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    
}