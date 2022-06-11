package com.gsnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsnotes.bo.Module;
import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.IModuleDao;
import com.gsnotes.services.IModuleService;


@Service
@Transactional
public class IModuleServiceImpl implements  IModuleService {
	
	
	@Autowired
	private IModuleDao  moduleDao ;
	
	

	@Override
	public List<Module> getByIdNiveau(Niveau niveau) {
		
		
		return moduleDao.findAllByNiveau( niveau);

    }
}
