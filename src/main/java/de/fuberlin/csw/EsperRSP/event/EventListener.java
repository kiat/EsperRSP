package de.fuberlin.csw.EsperRSP.event;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.espertech.esper.event.bean.BeanEventBean;
import com.espertech.esper.event.map.MapEventBean;

public class EventListener implements UpdateListener {
	static Logger log = Logger.getLogger(EventListener.class);

	public boolean flag = false;

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		System.out.println("\nNEW COMPLEX EVENT");

		if (newEvents != null) {
			for (EventBean newEvent : newEvents) {

				// EventBean newEvent = newEvents[0];
				if (newEvent instanceof BeanEventBean) {

					TripleEvent sEvent = (TripleEvent) newEvent.getUnderlying();
					System.out.println("MATCH Triple:  s:" + sEvent.subject + " ,  p:" + sEvent.predicate + " ,  o: " + sEvent.object);

				} else if (newEvent instanceof EventBean) {

					MapEventBean bean = (MapEventBean) newEvent;
					HashMap<?, ?> map = (HashMap<?, ?>) bean.getUnderlying();

					for (Map.Entry<?, ?> entry : map.entrySet()) {

						if (entry.getValue() instanceof BeanEventBean) {

							BeanEventBean tmp = (BeanEventBean) entry.getValue();

							// check whether event is instance of
							if (tmp.getUnderlying().getClass() == TripleEvent.class) {
								// retrieve the actual event from bean
								TripleEvent sEvent = (TripleEvent) tmp.getUnderlying();

								// here we send the enriched Event
								System.out.println("MATCH Triple:  s:" + sEvent.subject + " ,  p:" + sEvent.predicate + " ,  o: " + sEvent.object);
							}
						}
					} // END OF FOR
				} else {
					System.err.println("ERROR");
					log.debug("Event received not of type TripleEvent.class!");
				}
			}
		}else {
			System.err.println("OBJECT is null");
			log.debug("newEvents are null");
		}
	}
}