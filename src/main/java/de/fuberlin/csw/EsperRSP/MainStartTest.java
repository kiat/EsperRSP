package de.fuberlin.csw.EsperRSP;

import org.apache.log4j.PropertyConfigurator;

import de.fuberlin.csw.EsperRSP.event.EsperEventProcessor;
import de.fuberlin.csw.EsperRSP.event.EventListener;

public class MainStartTest {

	static String query0="select * from TripleEvent(predicate='isIn')";

	//  Who is where? every 1 min?
	static String query1="select * from TripleEvent(predicate='isIn').win:time(60 sec)";

	static String query1_1="select * from TripleEvent(subject='Person-a').win:time(1 sec)";
	static String query1_2="select * from TripleEvent(object='Room-a').win:time(1 sec)";

	// Find person who has been to more than 3 different rooms during the past 5 minutes (relies on SPARQL 1.1 aggregation)
	static String query2="select * from pattern [every (a=TripleEvent ->  b=TripleEvent ->  c=TripleEvent) where timer:within(5 min)] where a.subject=b.subject   and "
			+ "b.subject=c.subject  and  a.object!=b.object and  b.object!=c.object and a.object!=c.object  ";
	
	static String query2_1="select * from pattern [ every a=TripleEvent(subject='Person-a')  ]";
	static String query2_2="select * from pattern [ every-distinct(a.object) a=TripleEvent(subject='Person-a')  ]";


	

	
	
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		
		EsperEventProcessor ep= new EsperEventProcessor();
		EventListener my_listener=new EventListener();

		ep.addPattern(query2, my_listener);
		ep.start();
	}

}
