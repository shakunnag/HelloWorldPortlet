package com.lbg.cbo.HelloWorldPortlet;

import org.ff4j.FF4j;
import org.ff4j.web.FF4jProvider;

public class MyFF4jProvider implements FF4jProvider {

	private final FF4j ff4j;

	/**
	 * Default constructor
	 */
	public MyFF4jProvider() {
		ff4j = ProgramFF4JProvider.getInstance();
	}

	/** Method expected by Interface FF4JProvider */
	public FF4j getFF4j() {
		return ff4j;
	}
}