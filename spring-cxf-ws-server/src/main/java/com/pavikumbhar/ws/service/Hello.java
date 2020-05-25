package com.pavikumbhar.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://pavikumbhar.ws/", name = "Hello")
public interface Hello {
    
    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://pavikumbhar.ws/", className = "com.pavikumbhar.javaheart.ws.SayHello")
    @WebMethod(action = "urn:SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://pavikumbhar.ws/", className = "com.pavikumbhar.ws.service.SHelloResponse")
    String sayHello(@WebParam(name = "myname", targetNamespace = "") String myname);
}