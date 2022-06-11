package com.gsnotes.services;

import java.util.List;

import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.Niveau;

public interface IInscriptionAnnuelleService {
	
	public List<InscriptionAnnuelle> getByNiveau(Niveau n);
	

}
