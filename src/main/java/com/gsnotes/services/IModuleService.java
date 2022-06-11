package com.gsnotes.services;

import java.util.List;

import com.gsnotes.bo.Module;
import com.gsnotes.bo.Niveau;

public interface IModuleService {

	List<Module> getByIdNiveau(Niveau niveau);

}
