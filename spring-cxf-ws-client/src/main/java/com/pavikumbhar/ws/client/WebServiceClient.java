package com.pavikumbhar.ws.client;

import static org.springframework.util.StringUtils.isEmpty;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import lombok.extern.slf4j.Slf4j;




/**
 *  Class to configure and make a webservice call
 * 
 *  @author pavikumbhar
 *
 */

@Slf4j
@Component
public class WebServiceClient {

	
	@Resource
	private WebServiceTemplate webServicetemplate;

	/**
	 * 
	 * @param xml
	 * @param uri
	 * @return
	 */
	public String wsSynchCall(String xml, String uri) {
		log.info("wsSynchCall : ur i:  {} ", uri);
		webServicetemplate.setDefaultUri(uri);
		webServicetemplate.setMessageSender(httpComponentsMessageSender(null, null));
		StreamSource source = new StreamSource(new StringReader(xml));
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		webServicetemplate.sendSourceAndReceiveToResult(source, result);
		return streamResultToString(result);
	}
	
	
	
	/**
	 * 
	 * @param xml
	 * @param uri
	 * @param soapAction
	 * @param username
	 * @param password
	 * @return
	 */
	public String wsSynchCall(String xml, String uri, String soapAction) {
		log.info("wsSynchCall : uri:  {}  soapAction : {} ", uri,soapAction);
		webServicetemplate.setDefaultUri(uri);
		webServicetemplate.setMessageSender(httpComponentsMessageSender(null, null));
		StreamSource source = new StreamSource(new StringReader(xml));
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		webServicetemplate.sendSourceAndReceiveToResult(source, new WebServiceMessageCallbackImpl(soapAction), result);
		return streamResultToString(result);
	}

	/**
	 * 
	 * @param xml
	 * @param uri
	 * @param username
	 * @param password
	 * @return
	 */
	public String wsSynchCall(String xml, String uri, String username, String password) {
		log.info("wsSynchCall : uri:  {}  ", uri);
		webServicetemplate.setDefaultUri(uri);
		webServicetemplate.setMessageSender(httpComponentsMessageSender(username, password));
		StreamSource source = new StreamSource(new StringReader(xml));
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		webServicetemplate.sendSourceAndReceiveToResult(source, result);
		return streamResultToString(result);
	}

	/**
	 * 
	 * @param xml
	 * @param uri
	 * @param soapAction
	 * @param username
	 * @param password
	 * @return
	 */
	public String wsSynchCall(String xml, String uri, String soapAction, String username, String password) {
		log.info("wsSynchCall : uri:  {}  soapAction : {} ", uri,soapAction);
		webServicetemplate.setDefaultUri(uri);
		webServicetemplate.setMessageSender(httpComponentsMessageSender(username, password));
		StreamSource source = new StreamSource(new StringReader(xml));
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		webServicetemplate.sendSourceAndReceiveToResult(source, new WebServiceMessageCallbackImpl(soapAction), result);
		return streamResultToString(result);
	}

	
	/**
	 * 
	 * @param xml
	 * @param uri
	 * @param soapAction
	 * @return
	 */
	public StreamResult wsAsynchCall(String xml, String uri) {
		log.info("wsAsynchCall : uri:  {}   ", uri);
		webServicetemplate.setDefaultUri(uri);
		webServicetemplate.setMessageSender(httpComponentsMessageSender(null, null));
		StreamSource source = new StreamSource(new StringReader(xml));
		StreamResult result = new StreamResult(System.out);
		webServicetemplate.sendSourceAndReceiveToResult(source,  result);
		return result;
	}
	
	/**
	 * 
	 * @param xml
	 * @param uri
	 * @param soapAction
	 * @return
	 */
	public StreamResult wsAsynchCall(String xml, String uri, String soapAction) {
		webServicetemplate.setDefaultUri(uri);
		webServicetemplate.setMessageSender(httpComponentsMessageSender(null, null));
		StreamSource source = new StreamSource(new StringReader(xml));
		StreamResult result = new StreamResult(System.out);
		webServicetemplate.sendSourceAndReceiveToResult(source, new WebServiceMessageCallbackImpl(soapAction), result);
		return result;
	}


	/**
	 * 
	 * @param xml
	 * @param uri
	 * @param username
	 * @param password
	 * @return
	 */
	public StreamResult wsAsynchCall(String xml, String uri,  String username, String password) {
		webServicetemplate.setDefaultUri(uri);
		webServicetemplate.setMessageSender(httpComponentsMessageSender(username, password));
		StreamSource source = new StreamSource(new StringReader(xml));
		StreamResult result = new StreamResult(System.out);
		webServicetemplate.sendSourceAndReceiveToResult(source,  result);
		return result;
	}
	/**
	 * 
	 * @param xml
	 * @param uri
	 * @param soapAction
	 * @param username
	 * @param password
	 * @return
	 */
	public StreamResult wsAsynchCall(String xml, String uri, String soapAction, String username, String password) {
		webServicetemplate.setDefaultUri(uri);
		webServicetemplate.setMessageSender(httpComponentsMessageSender(username, password));
		StreamSource source = new StreamSource(new StringReader(xml));
		StreamResult result = new StreamResult(System.out);
		webServicetemplate.sendSourceAndReceiveToResult(source, new WebServiceMessageCallbackImpl(soapAction), result);
		return result;
	}

	private CredentialsProvider getcredentialsProvider(String username, String password) {
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
		return credentialsProvider;

	}

	private HttpComponentsMessageSender httpComponentsMessageSender(String username, String password) {
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		httpClientBuilder.addInterceptorFirst(new HttpComponentsMessageSender.RemoveSoapHeadersInterceptor());
		if (!isEmpty(username) && !isEmpty(password)) {
			httpClientBuilder.setDefaultCredentialsProvider(getcredentialsProvider(username, password));
		}
		HttpClient httpClient = httpClientBuilder.build();
		httpComponentsMessageSender.setHttpClient(httpClient);
		return httpComponentsMessageSender;
	}

	private String streamResultToString(StreamResult result) {
	    StringWriter sw = (StringWriter) result.getWriter();
        StringBuffer sb = sw.getBuffer();
        
   
        return sb.toString();
		
	}
    
	
	public static class WebServiceMessageCallbackImpl implements WebServiceMessageCallback {
		private String soapAction;

		public WebServiceMessageCallbackImpl(String soapAction) {
			this.soapAction = soapAction;
		}

		@Override
		public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
			((SoapMessage) message).setSoapAction(soapAction);

		}
	}
	
	


}
