package de.fuberlin.csw.EsperRSP.event;

import java.util.Random;

import org.apache.log4j.Logger;

import de.fuberlin.csw.EsperRSP.data.RandomString;

public class EventProducer {

	static Logger log = Logger.getLogger(EventProducer.class);
	static long eventCount = 0;
	RandomString randomString;

	public EventProducer() {
		randomString = new RandomString(1);
	}

	/*
	 * generate the next event Tuple
	 */
	public TripleEvent nextTuple() {

		TripleEvent currentStock = new TripleEvent("Person-" + randomString.nextString(), "isIn", "Room-" + randomString.nextString());

		eventCount += 1;
		return currentStock;
	}

	/*
	 * generate the next event Tuple
	 */
	public TripleEvent nextTupleDegree() {

		Random rand = new Random();
		// a random number between 1-10
		int randomNum = rand.nextInt((10 - 1) + 1) + 1;
		TripleEvent currentStock;
		if (randomNum >= 5)
			currentStock = new TripleEvent("Person-" + randomString.nextString(), "hasDegree", "doctorate");
		else
			currentStock = new TripleEvent("Person-" + randomString.nextString(), "hasDegree", "master");

		eventCount += 1;
		return currentStock;
	}

}
