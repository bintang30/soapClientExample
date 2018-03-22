package com.muf.soap3.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.tempuri.TestConnection;
import org.tempuri.TestConnectionResponse;
import org.tempuri.ViewImage;
import org.tempuri.ViewImageResponse;

import com.microsoft.schemas._2003._10.serialization.arrays.ObjectFactory;

@RestController
public class DMSController{
	
	@Autowired
	WebServiceTemplate webServiceTemplate;
	
	@RequestMapping(value="/test2", method=RequestMethod.GET)
	public void test(){
		TestConnection request = new TestConnection();
		TestConnectionResponse response = (TestConnectionResponse) webServiceTemplate.marshalSendAndReceive(request, new SoapActionCallback("http://tempuri.org/ILDMSDocWS/testConnection"));
		System.out.println(response.getTestConnectionResult());
	}
	
	@RequestMapping(value="/getImage", method=RequestMethod.GET)
	public void getImage(){
		org.tempuri.ObjectFactory objectFactory = new org.tempuri.ObjectFactory();
		ViewImage request = new ViewImage();
		QName qName = new QName("http://tempuri.org/", "ViewImage");
//		JAXBElement<String> hostname = new JAXBElement<String>(qName, String.class, "16002187");
//		JAXBElement<String> id = new JAXBElement<String>(qName, String.class, "010216100527");
//		JAXBElement<String> jenisDoc = new JAXBElement<String>(qName, String.class, "1");
//		JAXBElement<String> taskId = new JAXBElement<String>(qName, String.class, "5187");
//		JAXBElement<String> type = new JAXBElement<String>(qName, String.class, "COLL");
//		JAXBElement<String> username = new JAXBElement<String>(qName, String.class, "admin");
//		JAXBElement<String> reference = new JAXBElement<String>(qName, String.class, " ");
		request.setHostName(objectFactory.createViewImageHostName("16002187"));
		request.setID(objectFactory.createViewImageID("010216100527"));
		request.setJenisDoc(objectFactory.createViewImageJenisDoc("1"));
		request.setTaskID(objectFactory.createViewImageTaskID("5187"));
		request.setType(objectFactory.createViewImageType("COLL"));
		request.setUserName(objectFactory.createViewImageUserName("admin"));
		
		ViewImageResponse response = (ViewImageResponse) webServiceTemplate.marshalSendAndReceive(request, new SoapActionCallback("http://tempuri.org/ILDMSDocWS/ViewImage"));
		System.out.println(response.getByteImage().getValue());
		System.out.println(response.getFileName().getValue());
		System.out.println(response.getViewImageResult().getValue());
	}
}
