<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>

<%
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Credentials", "true");
    response.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String title = request.getParameter("title");
    if (title != null && !title.equals("")) {
    
	    String category = request.getParameter("category");
	    String type = request.getParameter("type");
	    String description = request.getParameter("description");
	    Date date = new Date();
	    String dateStr = df.format(date.getTime());
	    Entity shareinfo = new Entity("shareinfo");
	    shareinfo.setProperty("title", title);
	    shareinfo.setProperty("category", category);
	    shareinfo.setProperty("type", type);
	    shareinfo.setProperty("description", description);
	    shareinfo.setProperty("date", dateStr);
	
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	    datastore.put(shareinfo);
    }
%>
