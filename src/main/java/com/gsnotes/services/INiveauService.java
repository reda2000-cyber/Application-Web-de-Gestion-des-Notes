package com.gsnotes.services;


import java.util.List;

import com.gsnotes.bo.Niveau;
import com.gsnotes.utils.export.ExcelExporter;

public interface INiveauService {
	
	public List<Niveau> findAll();
	public ExcelExporter prepareDelibExport(Niveau niveau);

	

}
