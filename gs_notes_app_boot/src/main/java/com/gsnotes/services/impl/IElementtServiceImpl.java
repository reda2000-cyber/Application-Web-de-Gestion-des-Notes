package com.gsnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsnotes.bo.Element;
import com.gsnotes.bo.Module;
import com.gsnotes.dao.IElementDao;
import com.gsnotes.services.IElementService;




@Service
@Transactional
public class IElementtServiceImpl implements IElementService{
	
	
	@Autowired
	private IElementDao  elementDao ;

	@Override
	public List<Element> getMatiereByIdModule(Module module) {
		// TODO Auto-generated method stub
		return elementDao.findAllByModule(module);
	}
	

}
