package de.fuberlin.csw.EsperRSP.data;

import java.util.Random;

public class RandomString {

	  private static char[] symbols;

	  static {
	    StringBuilder tmp = new StringBuilder();
	    for (char ch = 'a'; ch <= 'f'; ++ch)
	      tmp.append(ch);
	    symbols = tmp.toString().toCharArray();
	  }   

	  private final Random random = new Random();

	  private final char[] buf;

	  public RandomString(int length) {
	    if (length < 1)
	      throw new IllegalArgumentException("length < 1: " + length);
	    buf = new char[length];
	  }

	  public String nextString() {
	    for (int idx = 0; idx < buf.length; ++idx) 
	      buf[idx] = symbols[random.nextInt(symbols.length)];
	    return new String(buf);
	  }
	}