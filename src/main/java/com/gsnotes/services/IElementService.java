package com.gsnotes.services;

import java.util.List;

import com.gsnotes.bo.Element;
import com.gsnotes.bo.Module;

public interface IElementService {
	
	public List<Element> getMatiereByIdModule(Module module);

}
