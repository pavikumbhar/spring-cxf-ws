package com.pavikumbhar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tempuri.Add;
import org.tempuri.AddResponse;

import com.pavikumbhar.ws.client.WebServiceClient;
import com.pavikumbhar.ws.client.XMLMarshaller;
import com.w3schools.convert.generated.FahrenheitToCelsius;
import com.w3schools.convert.generated.FahrenheitToCelsiusResponse;

import lombok.extern.slf4j.Slf4j;
import ws.pavikumbhar.SayHello;
import ws.pavikumbhar.SayHelloResponse;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCxfWsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebServiceClientTests {
    
    @Autowired
    private WebServiceClient webServiceClient;
    

    
    @Test
    public void wsSynchCall() {
        SayHello say = new SayHello();
        say.setMyname("cx");
     
        String requestXml = XMLMarshaller.marshal(say, "http://pavikumbhar.ws/", "sayHello");
   
        log.info("requestXml: {} " , requestXml);
        String resultxml = webServiceClient.wsSynchCall(requestXml,"http://localhost:7878/services/hello?wsdl");
     
        SayHelloResponse response = XMLMarshaller.unMarshal(resultxml, SayHelloResponse.class);
        
        String responseXml = XMLMarshaller.marshal(response, "http://pavikumbhar.ws/", "sayHelloResponse");
        log.info("responseXml: {} " , responseXml);
       
        
    }
   
  
    @Test
    public void wsSynchAdd() {
    	Add add = new Add();
    	add.setIntA(10);
    	add.setIntB(20);
        String requestXml = XMLMarshaller.marshal(add, "http://tempuri.org/", "Add");
        
        log.info("requestXml: {} " , requestXml);
        String soapAction="http://tempuri.org/Add";
        String uri="http://www.dneonline.com/calculator.asmx?wsdl";
        String resultxml = webServiceClient.wsSynchCall(requestXml,uri,soapAction);
    
        AddResponse response = XMLMarshaller.unMarshal(resultxml, AddResponse.class);
        
        String responseXml = XMLMarshaller.marshal(response, "http://tempuri.org/", "AddResponse");
        log.info("responseXml: {} " , responseXml);
       
        
    }
    
    @Test
    public void wsSynchFahrenheitToCelsius() {
    	FahrenheitToCelsius fahrenheitToCelsius = new FahrenheitToCelsius();
    	fahrenheitToCelsius.setFahrenheit("100");
        String requestXml = XMLMarshaller.marshal(fahrenheitToCelsius, "https://www.w3schools.com/xml/", "FahrenheitToCelsius");
        
        log.info("requestXml: {} " , requestXml);
        String soapAction="https://www.w3schools.com/xml/FahrenheitToCelsius";
        String uri="https://www.w3schools.com/xml/tempconvert.asmx?WSDL";
        String resultxml = webServiceClient.wsSynchCall(requestXml,uri,soapAction);
    
        FahrenheitToCelsiusResponse response = XMLMarshaller.unMarshal(resultxml, FahrenheitToCelsiusResponse.class);
        
        String responseXml = XMLMarshaller.marshal(response,  "https://www.w3schools.com/xml/", "FahrenheitToCelsiusResponse");
        log.info("responseXml: {} " , responseXml);
       
        
    }
    
    

   
    
  

  
}