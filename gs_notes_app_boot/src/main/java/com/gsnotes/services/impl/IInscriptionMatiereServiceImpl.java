package com.gsnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsnotes.bo.Element;
import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.InscriptionMatiere;
import com.gsnotes.dao.IInscriptionMatiereDao;
import com.gsnotes.services.IInscriptionMatiereService;

@Service
@Transactional
public class IInscriptionMatiereServiceImpl implements IInscriptionMatiereService {
	
	
	@Autowired
	private IInscriptionMatiereDao  inscriptionmatieretDao ;

	@Override
	public List<InscriptionMatiere> getByInscriptionAnnuelle(InscriptionAnnuelle insc) {
		// TODO Auto-generated method stub
		return inscriptionmatieretDao.findByInscriptionAnnuelle(insc);
	}

	@Override
	public List<InscriptionMatiere> getByElement(Element elt) {
		// TODO Auto-generated method stub
		return inscriptionmatieretDao.findByMatiere(elt);
	}

}
