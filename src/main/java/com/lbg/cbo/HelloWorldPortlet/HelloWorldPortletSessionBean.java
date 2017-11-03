package com.lbg.cbo.HelloWorldPortlet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * A sample Java bean that stores portlet instance data in portlet session.
 *
 */
public class HelloWorldPortletSessionBean {

	/**
	 * Last text for the text form
	 */
	private String formText = "";
	private String featureStatus = "";
	
	private String userStory3_1_status = "";
	private String userStory3_2_status = "";
	private Map featureMap = new ConcurrentHashMap();

	/**
	 * @return the featureMap
	 */
	public Map getFeatureMap() {
		return featureMap;
	}

	/**
	 * @param featureMap the featureMap to set
	 */
	public void setFeatureMap(Map featureMap) {
		this.featureMap = featureMap;
	}

	/**
	 * @return the userStory3_1_status
	 */
	public String getUserStory3_1_status() {
		return userStory3_1_status;
	}

	/**
	 * @param userStory3_1_status the userStory3_1_status to set
	 */
	public void setUserStory3_1_status(String userStory3_1_status) {
		this.userStory3_1_status = userStory3_1_status;
	}

	/**
	 * @return the userStory3_2_status
	 */
	public String getUserStory3_2_status() {
		return userStory3_2_status;
	}

	/**
	 * @param userStory3_2_status the userStory3_2_status to set
	 */
	public void setUserStory3_2_status(String userStory3_2_status) {
		this.userStory3_2_status = userStory3_2_status;
	}

	/**
	 * @return the featureEnabled
	 */
	public String getFeatureStatus() {
		return featureStatus;
	}

	/**
	 * @param featureEnabled
	 *            the featureEnabled to set
	 */
	public void setFeatureStatus(String featureStatus) {
		this.featureStatus = featureStatus;
	}

	/**
	 * Set last text for the text form.
	 * 
	 * @param formText
	 *            last text for the text form.
	 */
	public void setFormText(String formText) {
		this.formText = formText;
	}

	/**
	 * Get last text for the text form.
	 * 
	 * @return last text for the text form
	 */
	public String getFormText() {
		return this.formText;
	}

}
