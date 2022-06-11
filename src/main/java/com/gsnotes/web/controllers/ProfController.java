package com.gsnotes.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gsnotes.bo.Niveau;
import com.gsnotes.services.INiveauService;
import com.gsnotes.utils.export.ExcelExporter;



@Controller
@RequestMapping("/prof")
public class ProfController {
	

	@Autowired
	private INiveauService niveauService;

	
	@RequestMapping("/choixNiveauForm")
	public String index1(Model model) {
		
		List<Niveau> LN=niveauService.findAll();
		model.addAttribute("listniveau",LN);

		return "prof/choixNiveauForm";
	}
	
	
	@RequestMapping("/choixNiveau")
	public void index(HttpServletResponse response,@ModelAttribute("Niveau") Niveau niveau) throws IOException {
		
		ExcelExporter excelDelib = niveauService.prepareDelibExport(niveau);
		
	    excelDelib.export1(response);

    }


}
