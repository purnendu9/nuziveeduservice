package com.nuziveedu.jdo;

import java.util.List;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.nuziveedu.jdo.domain.NuziveeduForm;
import com.nuziveedu.service.dto.NuziveeduFormDTO;

public class FormPersistenceManager {
	public void saveForm(NuziveeduFormDTO formData) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		NuziveeduForm nuziveeduForm = new NuziveeduForm(formData.getUserID(),
				formData.getFormData());
		try {
			pm.makePersistent(nuziveeduForm);
		} finally {
			pm.close();
		}
	}

	public String getFormList(String userID) {
		String formList = "";
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(NuziveeduForm.class);
		query.setFilter("userID == userIDParam");
		query.setOrdering("formID desc");
		query.declareParameters("String userIDParam");
		try {
			List<NuziveeduForm> forms = (List<NuziveeduForm>) query
					.execute(userID);
			if (!forms.isEmpty()) {
				for (NuziveeduForm nuziveeduForm : forms) {
					formList = formList + nuziveeduForm.getFormID();
					formList = formList + "~";
				}
			}

		} finally {
			pm.close();
		}
		return formList.substring(0, formList.length()-1);
	}

	public NuziveeduForm getFormByID(String formID) {
		NuziveeduForm nuziveeduForm = null;
		List<NuziveeduForm> forms;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(NuziveeduForm.class);
		query.setFilter("formID == formIDParam");
		query.declareParameters("Long formIDParam");
		try {
			forms = (List<NuziveeduForm>) query.execute(Long.valueOf(formID));
			System.out.println(forms.size());
		} finally {
			pm.close();
		}
		nuziveeduForm = forms.get(0);
		return nuziveeduForm;
	}
}
