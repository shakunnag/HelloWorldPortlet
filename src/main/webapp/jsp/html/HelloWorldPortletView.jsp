<%@page session="false" contentType="text/html" pageEncoding="ISO-8859-1" import="java.util.*,javax.portlet.*,com.lbg.cbo.HelloWorldPortlet.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<%
	com.lbg.cbo.HelloWorldPortlet.HelloWorldPortletSessionBean sessionBean = (com.lbg.cbo.HelloWorldPortlet.HelloWorldPortletSessionBean)renderRequest.getPortletSession().getAttribute(com.lbg.cbo.HelloWorldPortlet.HelloWorldPortlet.SESSION_BEAN);
%>
 

<DIV style="margin: 6px">

<H3 style="margin-bottom: 3px">Welcome!</H3>
This is a sample portlet to showcase RSA and Docker Image (WebSphere Portal and WAS) integration <BR>
<H3 style="margin-bottom: 3px">Order entry</H3>
This is a sample form to test action handling.

<DIV style="margin: 12px; margin-bottom: 36px">
<% /******** Start of sample code ********/ %>

<%
	String formText = sessionBean.getFormText();
	if( formText.length()>0 ) {
		%>
		User Name entered by you <b>"<%=formText%>"</b> should be displayed here.
		<%
	}
	%>
	<FORM method="POST" action="<portlet:actionURL/>">
		<LABEL  for="<%=com.lbg.cbo.HelloWorldPortlet.HelloWorldPortlet.FORM_TEXT%>">Enter User Name:</LABEL><BR>
		<INPUT name="<%=com.lbg.cbo.HelloWorldPortlet.HelloWorldPortlet.FORM_TEXT%>" type="text"/>
		<INPUT name="<%=com.lbg.cbo.HelloWorldPortlet.HelloWorldPortlet.FORM_SUBMIT%>" type="submit" value="Submit"/>
	</FORM>
<% /******** End of sample code *********/ %>
</DIV>

</DIV>