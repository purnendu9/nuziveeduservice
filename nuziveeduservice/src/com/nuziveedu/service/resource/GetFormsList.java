package com.nuziveedu.service.resource;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.nuziveedu.jdo.FormPersistenceManager;

public class GetFormsList extends ServerResource {
	FormPersistenceManager formPersistenceManager = new FormPersistenceManager();
	@Get
	public Representation getForms() {
		Representation response = null;
		try {
			String username = (String) getRequest().getAttributes().get(
					"handle");
			if (null != username) {
				setStatus(Status.SUCCESS_OK);
				response = new StringRepresentation(
						formPersistenceManager.getFormList(username));
			} else
				throw new Exception("handle was null");
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			setStatus(Status.SERVER_ERROR_INTERNAL);
			response = new StringRepresentation("ERROR : " + sw.toString());
		}
		return response;
	}
}
