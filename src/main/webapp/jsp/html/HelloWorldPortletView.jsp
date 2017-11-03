<%@page session="false" contentType="text/html" pageEncoding="ISO-8859-1" import="java.util.*,javax.portlet.*,com.lbg.cbo.HelloWorldPortlet.*,java.util.Map.Entry,org.ff4j.core.Feature" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v6.1/portlet-client-model" prefix="portlet-client-model" %>    
<%@ taglib prefix="ff4j" uri="http://www.ff4j.org/taglibs/ff4j" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<portlet:defineObjects/>
<style type="text/css">
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
<%
	com.lbg.cbo.HelloWorldPortlet.HelloWorldPortletSessionBean sessionBean = (com.lbg.cbo.HelloWorldPortlet.HelloWorldPortletSessionBean)renderRequest.getPortletSession().getAttribute(com.lbg.cbo.HelloWorldPortlet.HelloWorldPortlet.SESSION_BEAN);
%>
<% 
	String featureStatus  =  sessionBean.getFeatureStatus();
	String featureStatus1 =  sessionBean.getUserStory3_1_status();
	String featureStatus2 =  sessionBean.getUserStory3_2_status();
	Map<String,Feature> myMap =  sessionBean.getFeatureMap();
%>
<div style="margin: 6px">
<table>
<tr>
<th>Feature Name</th>
<th>Feature Description</th>
<th>Feature Group</th>
<th>Feature State</th>
</tr>

<% for(Entry<String,Feature> entry : myMap.entrySet()) {
%>
<tr>
<td><%=entry.getKey() %></td>
<td><%=entry.getValue().getDescription() %></td>
<td><%=entry.getValue().getGroup() %></td>
<td><%=entry.getValue().isEnable() %></td>
</tr>
<%
}
%>

</table>
</div>
<div style="margin: 6px">
<table>
<tr><th>This piece of code demonstrates usage of taglib for FF4J</th></tr>
<ff4j:enable featureid="myFeature">
<tr><td>This code is displayed only if the myFeature is <b>UP</b></td></tr>
</ff4j:enable>

<ff4j:disable featureid="myFeature">
<tr><td>This code is displayed only if the myFeature is <b>DOWN</b></td></tr>
</ff4j:disable>
</table>
</div>

<DIV style="margin: 6px">
<H3 style="margin-bottom: 3px">Welcome!</H3>
This is a sample portlet to show case RSA and Docker Image (WebSphere Portal and WAS) integration <BR>
<H3 style="margin-bottom: 3px">User Name Entry</H3>
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
		<LABEL  for="<%=com.lbg.cbo.HelloWorldPortlet.HelloWorldPortlet.FORM_TEXT%>">Enter kuch bhi here:</LABEL><BR>
		<INPUT name="<%=com.lbg.cbo.HelloWorldPortlet.HelloWorldPortlet.FORM_TEXT%>" type="text"/>
		<INPUT name="<%=com.lbg.cbo.HelloWorldPortlet.HelloWorldPortlet.FORM_SUBMIT%>" type="submit" value="Submit"/>
	</FORM>
<% /******** End of sample code *********/ %>
</DIV>

</DIV>