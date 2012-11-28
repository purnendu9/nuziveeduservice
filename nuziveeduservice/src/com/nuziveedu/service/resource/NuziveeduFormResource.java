package com.nuziveedu.service.resource;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.nuziveedu.jdo.FormPersistenceManager;
import com.nuziveedu.jdo.domain.NuziveeduForm;
import com.nuziveedu.service.dto.NuziveeduFormDTO;

public class NuziveeduFormResource extends ServerResource {
	NuziveeduFormDTO nuziveeduFormDTO;
	FormPersistenceManager formPersistenceManager = new FormPersistenceManager();

	@Get
	public Representation getForm() {
		NuziveeduForm nuziveeduForm = null;
		Representation response = null;
		try {
			String formID = (String) getRequest().getAttributes().get("handle");
			if (null != formID) {
				nuziveeduForm = formPersistenceManager.getFormByID(formID);
				setStatus(Status.SUCCESS_OK);
				response = new StringRepresentation(nuziveeduForm.getFormData());
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

	@Post
	public Representation saveForm(Representation entity)
			throws ResourceException, IOException {
		nuziveeduFormDTO = new NuziveeduFormDTO();
		String parameterText = entity.getText();
		String[] parameters = parameterText.split("&");
		nuziveeduFormDTO.setUserID(parameters[0].substring(7,parameters[0].length()));
		nuziveeduFormDTO.setFormData(parameters[1].substring(9,parameters[1].length()));
		formPersistenceManager.saveForm(nuziveeduFormDTO);
		return null;
	}

}
