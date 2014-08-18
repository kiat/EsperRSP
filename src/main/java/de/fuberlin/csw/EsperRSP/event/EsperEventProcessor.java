package de.fuberlin.csw.EsperRSP.event;

import org.apache.log4j.Logger;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class EsperEventProcessor extends Thread {

	EventProducer event_generator;

	boolean forward;

	static final long serialVersionUID = 8L;

	static Logger log = Logger.getLogger(EsperEventProcessor.class);
	static Logger loggerResults = Logger.getLogger("esper");

	static EPServiceProvider epService;

	static EPAdministrator admin;
	EPRuntime runtime;
	
	String name;

	/** The underlying query describing the relationship */
	String patternQuery;

	/** The operators that describe sequence of events in pattern */
	String[] patternOps;

	/** The size of the sliding window of patterns */
	String patternWindowSize;

	EventListener listener;

	public EsperEventProcessor() {

		log.debug("\n=====================\n" + "= Startup Esper Event Processor =" + "\n=====================\n");

		// init Esper
		// configure and initialize Esper engine
		Configuration configuration = new Configuration();
		configuration.addEventType(TripleEvent.class);

		configuration.getEngineDefaults().getLogging().setEnableExecutionDebug(false);
		configuration.getEngineDefaults().getLogging().setEnableTimerDebug(false);

		epService = EPServiceProviderManager.getProvider(name, configuration);
		// EPServiceProviderManager.getDefaultProvider(configuration);
		admin = epService.getEPAdministrator();
		runtime = epService.getEPRuntime();

		log.debug("Esper EPService initialized...");
	}

	/**
	 * 
	 */
	public void run() {

		EventProducer ep = new EventProducer();
		log.info("started " + name);

		while (true) {
			TripleEvent currentEvent = ep.nextTuple();
			runtime.sendEvent(currentEvent);

			
//			System.out.println(event.subject + "  " + event.predicate + " " + event.object);

		}
		

	}

	/**
	 * This method adds an event processing pattern to Esper Engine
	 * 
	 * @param pattern
	 * @param listener
	 */
	public void addPattern(String pattern, EventListener listener) {

		log.debug("About to add pattern : " + pattern);

		EPStatement cepStatement = admin.createEPL(pattern);

		this.listener = listener;
		cepStatement.addListener(listener);

		log.debug("Pattern " + pattern + " added!");
	}

}