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
		this.subject = s;
		this.predicate = p;
		this.object = o;
	}

	public boolean getSubject(String value) {

		if (this.subject.equals(value)) {
			return true;
		} else
			return false;
	}
	
	
	public boolean getPredicate(String value) {

		if (this.predicate.equals(value)) {
			return true;
		} else
			return false;
	}

	public boolean getObject(String value) {

		if (this.object.equals(value)) {
			return true;
		} else
			return false;
	}
}
