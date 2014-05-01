package com.npu.shareinfo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONArray;

@SuppressWarnings("serial")
public class QueryRecordServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
    	JSONArray jsonArray = new JSONArray();
    	String qtitle = req.getParameter("title");
        if (qtitle == null) {
        	qtitle = "";
        }
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query("shareinfo").addSort("date", Query.SortDirection.DESCENDING);
        List<Entity> shareinfos = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(5));
        String title = "";
        if (!shareinfos.isEmpty()) {
        	for (Entity item : shareinfos) {
        		title = (String)item.getProperty("title");
        		if (title.toLowerCase().contains(qtitle.toLowerCase())){
        		jsonArray.put(ConvertEntityToJson.getJson(item));
        		}
            }
        	resp.setContentType("text/plain");
            resp.getWriter().println(jsonArray);
            req.getRequestDispatcher("/query.jsp").forward(req, resp);
        }
        
    }
}
