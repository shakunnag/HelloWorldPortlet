package com.lbg.cbo.HelloWorldPortlet;

import java.io.*;

import javax.portlet.*;

import com.lbg.cbo.HelloWorldPortlet.HelloWorldPortletSessionBean;

/**
 * A sample portlet based on GenericPortlet
 */
public class HelloWorldPortlet extends GenericPortlet {
	public static final String JSP_FOLDER    = "/jsp/";    // JSP folder name

	public static final String VIEW_JSP      = "HelloWorldPortletView";         // JSP file name to be rendered on the view mode
	public static final String EDIT_JSP      = "HelloWorldPortletEdit";         // JSP file name to be rendered on the edit mode
	
	public static final String HELP_JSP      = "HelloWorldPortletHelp";         // JSP file name to be rendered on the help mode
	
	public static final String CONFIG_JSP    = "HelloWorldPortletConfig";       // JSP file name to be rendered on the configure mode
	public static final String EDIT_DEFAULTS_JSP    = "HelloWorldPortletEditDefaults";       // JSP file name to be rendered on the configure mode
	
	public static final String SESSION_BEAN  = "HelloWorldPortletSessionBean";  // Bean name for the portlet session
	public static final String FORM_SUBMIT   = "HelloWorldPortletFormSubmit";   // Action name for submit form
	public static final String FORM_TEXT     = "HelloWorldPortletFormText";     // Parameter name for the text input
   
	public static final String EDIT_SUBMIT   = "HelloWorldPortletEditSubmit";   // Action name for submit form
	public static final String EDIT_TEXT     = "HelloWorldPortletEditText";     // Parameter name for the text input
	public static final String EDIT_KEY      = ".HelloWorldPortletEditKey";     // Key for the portlet preferences
	public static final String CONFIG_SUBMIT = "HelloWorldPortletConfigSubmit"; // Action name for submit form
	public static final String CONFIG_TEXT   = "HelloWorldPortletConfigText";   // Parameter name for the text input
	public static final String CONFIG_KEY    = ".HelloWorldPortletConfigKey";   // Key for the portlet preferences
	public static final String EDIT_DEFAULTS_SUBMIT = "HelloWorldPortletEditDefaultsSubmit"; // Action name for submit form
	public static final String EDIT_DEFAULTS_TEXT   = "HelloWorldPortletEditDefaultsText";   // Parameter name for the text input
	public static final String EDIT_DEFAULTS_KEY    = ".HelloWorldPortletEditDefaultsKey";   // Key for the portlet preferences

	private static final PortletMode CUSTOM_CONFIG_MODE = new PortletMode("config");

	private static final PortletMode CUSTOM_EDIT_DEFAULTS_MODE = new PortletMode("edit_defaults");



	 
	/**
	 * @see javax.portlet.Portlet#init()
	 */
	public void init() throws PortletException{
		super.init();
	}

	/**
	 * Serve up the <code>view</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		// Check if portlet session exists
		HelloWorldPortletSessionBean sessionBean = getSessionBean(request);
		if( sessionBean==null ) {
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}

		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, VIEW_JSP));
		rd.include(request,response);
	}

	/**
	 * Serve up the <code>edit</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doEdit(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		// Check if portlet session exists
		HelloWorldPortletSessionBean sessionBean = getSessionBean(request);
		if( sessionBean==null ) {
		    response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}


		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, EDIT_JSP));
		rd.include(request,response);
	}
	
	/**
	 * Serve up the <code>help</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doHelp(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	protected void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());
		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, HELP_JSP));
		rd.include(request,response);
	}

	/**
	 * Serve up the custom <code>config</code> mode.
	 */
	protected void doCustomConfigure(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());
		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, CONFIG_JSP));
		rd.include(request,response);
	}
	
	/**
	 * Serve up the custom <code>edit_defaults</code> mode.
	 */
	protected void doCustomEditDefaults(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());
		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, EDIT_DEFAULTS_JSP));
		rd.include(request,response);
	}
	
	/**
	 * Override doDispatch method for handling custom portlet modes.
	 * 
	 * @see javax.portlet.GenericPortlet#doDispatch(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	protected void doDispatch(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		if (!WindowState.MINIMIZED.equals(request.getWindowState())){
			PortletMode mode = request.getPortletMode();			
			if (CUSTOM_CONFIG_MODE.equals(mode)) {
				doCustomConfigure(request, response);
				return;
			}
			else if (CUSTOM_EDIT_DEFAULTS_MODE.equals(mode)) {
				doCustomEditDefaults(request, response);
				return;
			}
		}
		super.doDispatch(request, response);
	}

	/**
	 * Process an action request.
	 * 
	 * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, java.io.IOException {
		if( request.getParameter(FORM_SUBMIT) != null ) {
			// Set form text in the session bean
			HelloWorldPortletSessionBean sessionBean = getSessionBean(request);
			if( sessionBean != null )
				sessionBean.setFormText(request.getParameter(FORM_TEXT));
		}
		if( request.getParameter(EDIT_SUBMIT) != null ) {
			PortletPreferences prefs = request.getPreferences();
			try {
				prefs.setValue(EDIT_KEY,request.getParameter(EDIT_TEXT));
				prefs.store();
			}
			catch( ReadOnlyException roe ) {
			}
			catch( ValidatorException ve ) {
			}
		}
		if( request.getParameter(CONFIG_SUBMIT) != null ) {
			PortletPreferences prefs = request.getPreferences();
			try {
				prefs.setValue(CONFIG_KEY,request.getParameter(CONFIG_TEXT));
				prefs.store();
			}
			catch( ReadOnlyException roe ) {
			}
			catch( ValidatorException ve ) {
			}
		}
		if( request.getParameter(EDIT_DEFAULTS_SUBMIT) != null ) {
			PortletPreferences prefs = request.getPreferences();
			try {
				prefs.setValue(EDIT_DEFAULTS_KEY,request.getParameter(EDIT_DEFAULTS_TEXT));
				prefs.store();
			}
			catch( ReadOnlyException roe ) {
			}
			catch( ValidatorException ve ) {
			}
		}
	}

	/**
	 * Get SessionBean.
	 * 
	 * @param request PortletRequest
	 * @return HelloWorldPortletSessionBean
	 */
	private static HelloWorldPortletSessionBean getSessionBean(PortletRequest request) {
		PortletSession session = request.getPortletSession();
		if( session == null )
			return null;
		HelloWorldPortletSessionBean sessionBean = (HelloWorldPortletSessionBean)session.getAttribute(SESSION_BEAN);
		if( sessionBean == null ) {
			sessionBean = new HelloWorldPortletSessionBean();
			session.setAttribute(SESSION_BEAN,sessionBean);
		}
		return sessionBean;
	}

	/**
	 * Returns JSP file path.
	 * 
	 * @param request Render request
	 * @param jspFile JSP file name
	 * @return JSP file path
	 */
	private static String getJspFilePath(RenderRequest request, String jspFile) {
		String markup = request.getProperty("wps.markup");
		if( markup == null )
			markup = getMarkup(request.getResponseContentType());
		return JSP_FOLDER + markup + "/" + jspFile + "." + getJspExtension(markup);
	}

	/**
	 * Convert MIME type to markup name.
	 * 
	 * @param contentType MIME type
	 * @return Markup name
	 */
	private static String getMarkup(String contentType) {
		if( "text/vnd.wap.wml".equals(contentType) )
			return "wml";
        else
            return "html";
	}

	/**
	 * Returns the file extension for the JSP file
	 * 
	 * @param markupName Markup name
	 * @return JSP extension
	 */
	private static String getJspExtension(String markupName) {
		return "jsp";
	}

}
