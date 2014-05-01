<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.labs.repackaged.org.json.JSONArray" %>
<%@ page import="com.google.appengine.labs.repackaged.org.json.JSONObject" %>
<%@ page import="com.google.appengine.labs.repackaged.org.json.JSONException" %>
<%@ page import="java.util.Map.Entry" %>

<%
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Credentials", "true");
    JSONArray jsonArray = new JSONArray();
    String qtitle = request.getParameter("title");
    if (qtitle == null) {
    	qtitle = "";
    }
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Query query = new Query("shareinfo").addSort("date", Query.SortDirection.DESCENDING);
    List<Entity> shareinfos = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
    String title = "";
    if (!shareinfos.isEmpty()) {
    	for (Entity item : shareinfos) {
    		title = (String)item.getProperty("title");
    		if (title.toLowerCase().contains(qtitle.toLowerCase())){
                JSONObject jsonObject = new JSONObject();
				try {
					if (item.getKey().getName() != null) {
						jsonObject.put("ID", item.getKey().getName());
					} else {
						jsonObject.put("ID", item.getKey().getId());
					}
					for (Entry<String, Object> entry : item.getProperties()
							.entrySet()) {
						jsonObject.put(entry.getKey(), entry.getValue());
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				jsonArray.put(jsonObject);
    		}
        }
    	out.print(jsonArray);
    }
%>
