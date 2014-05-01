<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.IOException" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>

<%
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Credentials", "true");
    response.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");
    String infoid = request.getParameter("id");
	if (infoid != null) {
	
		Key key = KeyFactory.createKey("shareinfo", Long.parseLong(infoid));
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		datastore.delete(key);
	}
%>
