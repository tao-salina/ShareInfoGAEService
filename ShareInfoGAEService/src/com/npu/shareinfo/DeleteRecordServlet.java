package com.npu.shareinfo;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@SuppressWarnings("serial")
public class DeleteRecordServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String infoid = req.getParameter("infoid");
		if (infoid == null) {
			infoid = "";
		}
		System.out.println("infoid===" + infoid);
		Key key = KeyFactory.createKey("shareinfo", Long.parseLong(infoid));
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		datastore.delete(key);
		resp.sendRedirect("/queryinfo.html");
	}
}
