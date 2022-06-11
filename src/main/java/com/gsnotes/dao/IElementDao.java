package com.gsnotes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsnotes.bo.Element;
import com.gsnotes.bo.Module;


public interface IElementDao extends JpaRepository<Element, Long>{
	
	public List<Element> findAllByModule(Module module);

}
