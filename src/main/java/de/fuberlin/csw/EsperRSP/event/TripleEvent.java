package de.fuberlin.csw.EsperRSP.event;

import java.io.Serializable;

public class TripleEvent implements Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = 6523737083526253656L;

	public String subject;
	public String predicate;
	public String object;
	
	public TripleEvent() {

	}

	public TripleEvent(String s, String p, String o) {
		this.subject=s;
		this.predicate=p;
		this.object=o;
	}

}
