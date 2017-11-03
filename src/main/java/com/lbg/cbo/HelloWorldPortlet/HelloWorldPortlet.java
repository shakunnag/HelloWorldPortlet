package com.lbg.cbo.HelloWorldPortlet;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletSession;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;
import javax.portlet.WindowState;

import org.ff4j.FF4j;
import org.ff4j.core.FeatureStore;

/**
 * A sample portlet based on GenericPortlet
 */
public class HelloWorldPortlet extends GenericPortlet {
	public static final String JSP_FOLDER = "/jsp/"; // JSP folder name

	public static final String VIEW_JSP = "HelloWorldPortletView";
	public static final String EDIT_JSP = "HelloWorldPortletEdit";
	public static final String HELP_JSP = "HelloWorldPortletHelp";
	public static final String CONFIG_JSP = "HelloWorldPortletConfig";
	public static final String EDIT_DEFAULTS_JSP = "HelloWorldPortletEditDefaults";
	public static final String SESSION_BEAN = "HelloWorldPortletSessionBean";
	public static final String FORM_SUBMIT = "HelloWorldPortletFormSubmit";
	public static final String FORM_TEXT = "HelloWorldPortletFormText";
	public static final String EDIT_SUBMIT = "HelloWorldPortletEditSubmit";
	public static final String EDIT_TEXT = "HelloWorldPortletEditText";
	public static final String EDIT_KEY = ".HelloWorldPortletEditKey";
	public static final String CONFIG_SUBMIT = "HelloWorldPortletConfigSubmit";
	public static final String CONFIG_TEXT = "HelloWorldPortletConfigText";
	public static final String CONFIG_KEY = ".HelloWorldPortletConfigKey";
	public static final String EDIT_DEFAULTS_SUBMIT = "HelloWorldPortletEditDefaultsSubmit";
	public static final String EDIT_DEFAULTS_TEXT = "HelloWorldPortletEditDefaultsText";
	public static final String EDIT_DEFAULTS_KEY = ".HelloWorldPortletEditDefaultsKey";
	private static final PortletMode CUSTOM_CONFIG_MODE = new PortletMode("config");
	private static final PortletMode CUSTOM_EDIT_DEFAULTS_MODE = new PortletMode("edit_defaults");

	/**
	 * @see javax.portlet.Portlet#init()
	 */
	public void init() throws PortletException {
		super.init();
	}

	/**
	 * Serve up the <code>view</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest,
	 *      javax.portlet.RenderResponse)
	 */
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		// Check if portlet session exists
		HelloWorldPortletSessionBean sessionBean = getSessionBean(request);
		if (sessionBean == null) {
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}

		// Invoke the JSP to render

		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, VIEW_JSP));
		rd.include(request, response);

	}

	/**
	 * Serve up the <code>edit</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doEdit(javax.portlet.RenderRequest,
	 *      javax.portlet.RenderResponse)
	 */
	public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		// Check if portlet session exists
		HelloWorldPortletSessionBean sessionBean = getSessionBean(request);
		if (sessionBean == null) {
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}

		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, EDIT_JSP));
		rd.include(request, response);
	}

	/**
	 * Serve up the <code>help</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doHelp(javax.portlet.RenderRequest,
	 *      javax.portlet.RenderResponse)
	 */
	protected void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());
		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, HELP_JSP));
		rd.include(request, response);
	}

	/**
	 * Serve up the custom <code>config</code> mode.
	 */
	protected void doCustomConfigure(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());
		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, CONFIG_JSP));
		rd.include(request, response);
	}

	/**
	 * Serve up the custom <code>edit_defaults</code> mode.
	 */
	protected void doCustomEditDefaults(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());
		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext()
				.getRequestDispatcher(getJspFilePath(request, EDIT_DEFAULTS_JSP));
		rd.include(request, response);
	}

	/**
	 * Override doDispatch method for handling custom portlet modes.
	 * 
	 * @see javax.portlet.GenericPortlet#doDispatch(javax.portlet.RenderRequest,
	 *      javax.portlet.RenderResponse)
	 */
	protected void doDispatch(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		if (!WindowState.MINIMIZED.equals(request.getWindowState())) {
			PortletMode mode = request.getPortletMode();
			if (CUSTOM_CONFIG_MODE.equals(mode)) {
				doCustomConfigure(request, response);
				return;
			} else if (CUSTOM_EDIT_DEFAULTS_MODE.equals(mode)) {
				doCustomEditDefaults(request, response);
				return;
			}
		}
		super.doDispatch(request, response);
	}

	/**
	 * Process an action request.
	 * 
	 * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest,
	 *      javax.portlet.ActionResponse)
	 */

	public void processAction(ActionRequest request, ActionResponse response)
			throws PortletException, java.io.IOException {
		if (request.getParameter(FORM_SUBMIT) != null) {
			// Set form text in the session bean
			HelloWorldPortletSessionBean sessionBean = getSessionBean(request);
			FF4j ff4j = new MyFF4jProvider().getFF4j();
			Map myMap = new ConcurrentHashMap();
			
			System.out
					.println("The file ff4j exists....." + getClass().getClassLoader().getResourceAsStream("ff4j.xml"));
			System.out.println("Number of features..." + ff4j.getFeatures().size());
			System.out.println("Feature 1 exists..." + ff4j.exist("myFeature"));
			System.out.println("Feature 1 enabled..." + ff4j.check("myFeature"));
			
			FeatureStore featureStore = ff4j.getFeatureStore();
			myMap = featureStore.readAll();
			System.out.println(myMap);
			
			/*
			 * try { if (ff4j.check("anotherFeature")) System.out.
			 * println("By default, feature not found throw exception"); } catch
			 * (FeatureNotFoundException fnfe) {
			 * System.out.println(fnfe.getMessage()); }
			 * 
			 * // When: using autocreate ff4j.setAutocreate(true); // Then: no
			 * more exceptions if (!ff4j.check("anotherFeature")) {
			 * System.out.println("Auto created and set as false"); }
			 */
			/*System.out.println("userStory3_1 exists........" + ff4j.exist("userStory3_1"));
			System.out.println("userStory3_2 exists........" + ff4j.exist("userStory3_2"));
			System.out.println(
					"Feature group sprint_3 exists" + ff4j.getFeatureStore().readAllGroups().contains("sprint_3"));
			System.out.println("Feature Group is called..." + ff4j.getFeature("userStory3_1").getGroup());
			System.out.println("Feature Group is called..." + ff4j.getFeature("userStory3_2").getGroup());
			System.out.println("userStory3_1 enabled..." + ff4j.check("userStory3_1"));
			System.out.println("userStory3_2 enabled..." + ff4j.check("userStory3_2"));
*/
			// When: toggle group on
			/*
			 * ff4j.getFeatureStore().enableGroup("sprint_3");
			 * 
			 * // Then: all features on
			 * System.out.println("userStory3_1 enabled..."+ff4j.check(
			 * "userStory3_1"));
			 * System.out.println("userStory3_2 enabled..."+ff4j.check(
			 * "userStory3_2"));
			 */
			if (sessionBean != null)
				sessionBean.setFormText(request.getParameter(FORM_TEXT));
				sessionBean.setFeatureMap(myMap);
			if (ff4j.check("myFeature")) {
				sessionBean.setFeatureStatus("Enabled");
			} else {
				sessionBean.setFeatureStatus("Disabled");
			}
			if (ff4j.check("userStory3_1")) {
				sessionBean.setUserStory3_1_status("Enabled");
			} else {
				sessionBean.setUserStory3_1_status("Disabled");
			}
			if (ff4j.check("userStory3_2")) {
				sessionBean.setUserStory3_2_status("Enabled");
			} else {
				sessionBean.setUserStory3_2_status("Disabled");
			}
		}
		if (request.getParameter(EDIT_SUBMIT) != null) {
			PortletPreferences prefs = request.getPreferences();
			try {
				prefs.setValue(EDIT_KEY, request.getParameter(EDIT_TEXT));
				prefs.store();
			} catch (ReadOnlyException roe) {
			} catch (ValidatorException ve) {
			}
		}
		if (request.getParameter(CONFIG_SUBMIT) != null) {
			PortletPreferences prefs = request.getPreferences();
			try {
				prefs.setValue(CONFIG_KEY, request.getParameter(CONFIG_TEXT));
				prefs.store();
			} catch (ReadOnlyException roe) {
			} catch (ValidatorException ve) {
			}
		}
		if (request.getParameter(EDIT_DEFAULTS_SUBMIT) != null) {
			PortletPreferences prefs = request.getPreferences();
			try {
				prefs.setValue(EDIT_DEFAULTS_KEY, request.getParameter(EDIT_DEFAULTS_TEXT));
				prefs.store();
			} catch (ReadOnlyException roe) {
			} catch (ValidatorException ve) {
			}
		}
	}

	/**
	 * Get SessionBean.
	 * 
	 * @param request
	 *            PortletRequest
	 * @return HelloWorldPortletSessionBean
	 */
	private static HelloWorldPortletSessionBean getSessionBean(PortletRequest request) {
		PortletSession session = request.getPortletSession();
		if (session == null)
			return null;
		HelloWorldPortletSessionBean sessionBean = (HelloWorldPortletSessionBean) session.getAttribute(SESSION_BEAN);
		if (sessionBean == null) {
			sessionBean = new HelloWorldPortletSessionBean();
			session.setAttribute(SESSION_BEAN, sessionBean);
		}
		return sessionBean;
	}

	/**
	 * Returns JSP file path.
	 * 
	 * @param request
	 *            Render request
	 * @param jspFile
	 *            JSP file name
	 * @return JSP file path
	 */
	private static String getJspFilePath(RenderRequest request, String jspFile) {
		String markup = request.getProperty("wps.markup");
		if (markup == null)
			markup = getMarkup(request.getResponseContentType());
		return JSP_FOLDER + markup + "/" + jspFile + "." + getJspExtension(markup);
	}

	/**
	 * Convert MIME type to markup name.
	 * 
	 * @param contentType
	 *            MIME type
	 * @return Markup name
	 */
	private static String getMarkup(String contentType) {
		if ("text/vnd.wap.wml".equals(contentType))
			return "wml";
		else
			return "html";
	}

	/**
	 * Returns the file extension for the JSP file
	 * 
	 * @param markupName
	 *            Markup name
	 * @return JSP extension
	 */
	private static String getJspExtension(String markupName) {
		return "jsp";
	}

}
