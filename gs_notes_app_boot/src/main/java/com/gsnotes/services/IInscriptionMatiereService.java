package com.gsnotes.services;

import java.util.List;

import com.gsnotes.bo.Element;
import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.InscriptionMatiere;

public interface IInscriptionMatiereService {
	
	public List<InscriptionMatiere> getByInscriptionAnnuelle(InscriptionAnnuelle insc);
	
	public List<InscriptionMatiere> getByElement (Element elt);

}
