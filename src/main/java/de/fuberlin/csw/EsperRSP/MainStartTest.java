package de.fuberlin.csw.EsperRSP;

import org.apache.log4j.PropertyConfigurator;

import de.fuberlin.csw.EsperRSP.event.EsperEventProcessor;
import de.fuberlin.csw.EsperRSP.event.EventListener;

/**
 * 
 * @author Kia A test class for starting different Esper queries on example RDF
 *         stream for demonstration purposes.
 */
public class MainStartTest {

	static String query0 = "select * from TripleEvent(predicate='isIn')";

	// Query 1: Who is where? every 1 min?
	static String query1 = "select * from TripleEvent(predicate='isIn').win:time(60 sec)";

	static String query1_1 = "select * from TripleEvent(subject='Person-a').win:time(1 sec)";
	static String query1_2 = "select * from TripleEvent(object='Room-a').win:time(1 sec)";

	// Query 2: Find person who has been to more than 3 different rooms during
	// the past 5 minutes (relies on SPARQL 1.1 aggregation)
	static String query2 = "select * from pattern [every a=TripleEvent -> every b=TripleEvent -> every c=TripleEvent where timer:within(5 min)] where a.subject=b.subject   and "
			+ "b.subject=c.subject  and  a.object!=b.object and  b.object!=c.object and a.object!=c.object "
			+ "and a.predicate='isIn' and b.predicate='isIn' and c.predicate='isIn' ";

	static String query2_1 = "select * from pattern [ every a=TripleEvent(subject='Person-a')  ]";
	static String query2_2 = "select * from pattern [ every-distinct(a.object) a=TripleEvent(subject='Person-a')  ]";

	// Query 3: Detect two different people in the same room for at least three
	// seconds
	static String query3 = "select * from pattern [every  (a=TripleEvent -> b=TripleEvent) where timer:within(3 sec)] where a.subject!=b.subject and  a.object=b.object ";

	// Query 4: Detect persons who has entered either room-a or room-b in the
	// past 5 min
	static String query4 = "select * from pattern [every a=TripleEvent where timer:within(5 min)] where a.object='Room-a' or  a.object='Room-b'";

	// Query 5: Detect persons who entered room-a without a doctorate during the
	// past 5 minutes
	static String query5 = "select * from pattern [every b=TripleEvent -> a=TripleEvent where timer:within(5 min)] where a.object='Room-a' and b.object!='doctorate' and a.subject=b.subject";

	// Query 6: Provide pairs of rooms, e.g. (room-a, room-b), where Person-a
	// and Person-b have been together such that they are first in a room
	// and then following each other in another room within 5 minutes.
	static String query6 = "select * from pattern [every a=TripleEvent -> every b=TripleEvent -> every  c=TripleEvent -> every d=TripleEvent   where timer:within(5 min) ]"
			+ "  where a.subject!=b.subject and a.object=b.object and a.subject=c.subject and b.subject=d.subject and c.object=d.object"
			+ "  and a.predicate='isIn' and b.predicate='isIn' and c.predicate='isIn' and d.predicate='isIn' ";


	// Query 7: 'Query: Find at least 3 different pairs of rooms, in which Person-a and Person-b  have been together 
	// within 10 minutes, moving from room_a to room_b.
	// This query relies on a SEQ operator and on counting the repetition of
	// such sequence.
	
	static String query7 = "select * from pattern [ every  a=TripleEvent -> every b=TripleEvent  -> every  d=TripleEvent -> every c=TripleEvent -> every e=TripleEvent -> every f=TripleEvent    where timer:within(10 min)]"
			+ "where a.subject!=b.subject and a.object=b.object and   c.subject!=d.subject and c.object=d.object  and   e.subject!=f.subject and e.object=f.object  "
			+ "and a.subject=d.subject  and b.subject=c.subject and d.subject=e.subject and c.subject=f.subject "
			+ "and a.predicate='isIn' and b.predicate='isIn' and c.predicate='isIn' and d.predicate='isIn' and e.predicate='isIn' and f.predicate='isIn' ";
	
	
	
	

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");

		EsperEventProcessor ep = new EsperEventProcessor();
		EventListener my_listener = new EventListener();

		ep.addPattern(query2, my_listener);
		ep.start();

	}

}
