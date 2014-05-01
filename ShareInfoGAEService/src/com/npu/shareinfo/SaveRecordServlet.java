package com.npu.shareinfo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class SaveRecordServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException {
    	resp.addHeader("Access-Control-Allow-Origin", "*");
    	resp.addHeader("Access-Control-Allow-Credentials", "true");
    	resp.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");

    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String title = req.getParameter("title");
        String category = req.getParameter("category");
        String type = req.getParameter("type");
        String description = req.getParameter("description");
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

        //resp.sendRedirect("/newinfo.html");
    }
}
