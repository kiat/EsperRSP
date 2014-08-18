package de.fuberlin.csw.EsperRSP;

import org.apache.log4j.PropertyConfigurator;

import de.fuberlin.csw.EsperRSP.event.EsperEventProcessor;
import de.fuberlin.csw.EsperRSP.event.EventListener;

public class MainStartTest {

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");

		
		EsperEventProcessor ep= new EsperEventProcessor();
		EventListener my_listener=new EventListener();
		String pattern="select * from TripleEvent";
		
		ep.addPattern(pattern, my_listener);
		
		ep.start();
		
	}

}
