package de.fuberlin.csw.EsperRSP;

import org.apache.log4j.PropertyConfigurator;

import de.fuberlin.csw.EsperRSP.event.EsperEventProcessor;
import de.fuberlin.csw.EsperRSP.event.EventListener;

public class MainStartTest {

	//  Who is where? every 1 min?
	static String query1="select * from TripleEvent(predicate('isIn')=true).win:time(60 sec)";
	static String query1_1="select * from TripleEvent(subject('Person-a')=true).win:time(1 sec)";
	static String query1_2="select * from TripleEvent(object('Room-a')=true).win:time(1 sec)";

	// Find person who has been to more than 5 different rooms during the past 5 minutes (relies on SPARQL 1.1 aggregation)
	static String query2="select * from pattern [ every a=TripleEvent(subject('Person-a')=true)  ]";
	
	
	// select * from pattern [ every a=StockEvent(value('industry_§§§§_http://dbpedia.org/resource/Computer')=true)]
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");

		
		EsperEventProcessor ep= new EsperEventProcessor();
		EventListener my_listener=new EventListener();

		ep.addPattern(query2, my_listener);
		
		ep.start();
		
	}

}
