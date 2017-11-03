package com.lbg.cbo.HelloWorldPortlet;

import org.ff4j.FF4j;

public class ProgramFF4JProvider {

	private static FF4j instance = null;

	private ProgramFF4JProvider() {
	}
	public static FF4j getInstance() {
	      if(instance == null) {
	         instance = new FF4j("ff4j.xml");
	      }
	      return instance;
	   }
}
