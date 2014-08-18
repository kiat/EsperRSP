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

	int eventCount;
	String name;

	/** denotes the start time of flush */
	long startTime = 0;

	/** denotes the end time of flush */
	long endTime = 0;

	/** denotes the elapsed time between flushes */
	long elapsedTime = 0;

	public boolean flag = false;

	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		eventCount++;

		if (newEvents != null) {
			for (EventBean newEvent : newEvents) {

				// EventBean newEvent = newEvents[0];
				if (newEvent instanceof BeanEventBean) {

					TripleEvent sEvent = (TripleEvent) newEvent.getUnderlying();
					System.out.println(sEvent.subject + "  " + sEvent.predicate + "  " + sEvent.object);

					// } else {
					//
					// MapEventBean bean = (MapEventBean) newEvent;
					//
					// HashMap<?, ?> map = (HashMap<?, ?>) bean.getUnderlying();
					//
					// for (Map.Entry<?, ?> entry : map.entrySet()) {
					//
					// BeanEventBean tmp = (BeanEventBean) entry.getValue();
					//
					// // check whether event is instance of
					// if (tmp.getUnderlying().getClass() == TripleEvent.class)
					// {
					// // retrieve the actual event from bean
					//
					// TripleEvent sEvent = (TripleEvent) tmp.getUnderlying();
					//
					// // here we send the enriched Event
					// System.out.println(sEvent);
					// } else {
					//
					// log.debug("Event received not of type StockEvent.class!");
					// }
					// } // END OF FOR
				}
			}

		}

	}

}
