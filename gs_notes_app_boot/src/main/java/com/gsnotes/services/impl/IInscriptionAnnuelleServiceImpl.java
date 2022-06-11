package com.gsnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.IInscriptionAnnuelleDao;
import com.gsnotes.services.IInscriptionAnnuelleService;

@Service
@Transactional
public class IInscriptionAnnuelleServiceImpl implements IInscriptionAnnuelleService{
	
	@Autowired
	private IInscriptionAnnuelleDao  inscriptionannuelletDao ;

	@Override
	public List<InscriptionAnnuelle> getByNiveau(Niveau niveau) {
		return inscriptionannuelletDao.findAllByNiveau(niveau);
	}

}
