package com.gsnotes.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.VerticalAlignment;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsnotes.bo.Element;
import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.InscriptionMatiere;
import com.gsnotes.bo.Niveau;
import com.gsnotes.bo.Module;
import com.gsnotes.dao.INiveauDao;
import com.gsnotes.services.IElementService;
import com.gsnotes.services.IModuleService;
import com.gsnotes.services.INiveauService;
import com.gsnotes.utils.export.ExcelExporter;


@Service
@Transactional
public class INiveauServiceImpl implements INiveauService{
	
	@Autowired
	private INiveauDao niveauDao;
	@Autowired
	private IElementService elementService;
	@Autowired
	private IModuleService moduleService;
	@Autowired
	private IInscriptionAnnuelleServiceImpl inscripService;
	@Autowired
	private IInscriptionMatiereServiceImpl inscripmatiereService;
	


	
	@Override
	public List<Niveau> findAll() {
		// TODO Auto-generated method stub
		return niveauDao.findAll();
	}
	

    public ExcelExporter prepareDelibExport (Niveau niveau) {
    	
    	
    	

    	//Création d'un ojet ExcelExporter
    	ExcelExporter Excel = new ExcelExporter();
    	
    	//Récupération du workbook
    	XSSFWorkbook workbook = Excel.getWorkbook();
    	
    	//Récupération de la feuille
    	XSSFSheet sheet = Excel.getSheet();
    	
    	//Nommé la fueille
	    sheet = workbook.createSheet("Délibérations");
 
	    //Définir un style pour les cellules
    	CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        cellStyle.setBorderTop(BorderStyle.MEDIUM); 
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        	
	    //Définir un style pour les cellules constantes en vert
        CellStyle cStyle = workbook.createCellStyle();
        cStyle.setAlignment(HorizontalAlignment.CENTER);
        cStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cStyle.setWrapText(true);
        cStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
        cStyle.setBorderTop(BorderStyle.MEDIUM); 
        cStyle.setBorderBottom(BorderStyle.MEDIUM);
        cStyle.setBorderLeft(BorderStyle.MEDIUM);
        cStyle.setBorderRight(BorderStyle.MEDIUM);
        
        //Création du style de la ligne noire
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
        
    	//Création des cellules titre de base
        Row row = sheet.createRow((short) 0);
        row.setHeightInPoints(50);
        
        Cell cell0=row.createCell(0);
        cell0.setCellValue("Année Universitaire");
        cell0.setCellStyle(cStyle);
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 6000);
        
        Cell cell02=row.createCell(2);
        cell02.setCellValue("Date délibération");
        cell02.setCellStyle(cStyle);
        sheet.setColumnWidth(2, 6000);
    	
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String currentDateTime = dateFormatter.format(new Date());
    	Cell cell03=row.createCell(3);
        cell03.setCellValue(currentDateTime);
        cell03.setCellStyle(cellStyle);
        sheet.setColumnWidth(3, 6000);
        
        
        Row row01 = sheet.createRow((short) 1);
        row01.setHeightInPoints(50);
        Cell cell10=row01.createCell(0);
        cell10.setCellValue("Classe");
        cell10.setCellStyle(cStyle);
       
        //Création de la ligne noire
        Row row02 = sheet.createRow((short) 2);
        row02.setHeightInPoints(20);
        row02.setRowStyle(style);
        
        //Fixation de la hauteur de la ligne
    	Row row3 = sheet.createRow((short) 3);
    	row3.setHeightInPoints(50);
    	
    	//Définir les 4 colonnes de base de chaque étudiant
    	Cell cell30 = row3.createCell(0);
    	cell30.setCellValue("ID \n ETUDIANT");
    	cell30.setCellStyle(cellStyle);
    	
    	Cell cell31 = row3.createCell(1);
    	cell31.setCellValue("CNE");
    	cell31.setCellStyle(cellStyle);
    	
    	Cell cell32 = row3.createCell(2);
    	cell32.setCellValue("NOM");
    	cell32.setCellStyle(cellStyle);
    	
    	Cell cell33 = row3.createCell(3);
    	cell33.setCellValue("PRENOM");
    	cell33.setCellStyle(cellStyle);
    	
    	
    	
    	
    	
        //Récupération la liste des inscription annuelle lié au niveau
    	List<InscriptionAnnuelle> Insan= inscripService.getByNiveau(niveau);
    	
    	
    	//Remplir les cellules d'année scolaire et d'alias du niveau
    	int annee = Insan.get(0).getAnnee();
    	int annee1 = annee+1;
    	Cell cell01=row.createCell(1);
        cell01.setCellValue(annee+" / "+ annee1);
        cell01.setCellStyle(cellStyle);
       
        Cell cell11=row01.createCell(1);
        cell11.setCellValue(Insan.get(0).getNiveau().getAlias());
        cell11.setCellStyle(cellStyle);

    	
    	//Remplir les informations associées à chaque étudiant
    	int j=5;
    	for(InscriptionAnnuelle in :Insan) {
    		
    		Row rows = sheet.createRow((short) j);
    		Cell cell50 = rows.createCell(0);
    		cell50.setCellValue(in.getEtudiant().getIdUtilisateur());
    		cell50.setCellStyle(cellStyle);
    		
    		Cell cell51 = rows.createCell(1);
    		cell51.setCellValue(in.getEtudiant().getCne());
    		cell51.setCellStyle(cellStyle);
    		
    		
    		Cell cell52 = rows.createCell(2);
    		cell52.setCellValue(in.getEtudiant().getNom());
    		cell52.setCellStyle(cellStyle);
    		
    		
    		Cell cell53 = rows.createCell(3);
    		cell53.setCellValue(in.getEtudiant().getPrenom());
    		cell53.setCellStyle(cellStyle);
    		
    		j++;
    		
    	}
    	
    	//Hauteur de la ligne de la ligne 4
    	Row row4 = sheet.createRow((short) 4);
    	row4.setHeightInPoints(50);
    	
    	//Déclaration du SQtring qui va contenir la formule de la moyenne générale
    	String formulemoyennege="";
    	
    	
    	//Récupération des module à partir du niveau
        List<Module> module = moduleService.getByIdNiveau(niveau);
    	
        //Parcourir la liste des module du niveau associé
    	int i=4;    	
    	for(Module m : module) {
    		
    		//Créer la cellule du module
    		sheet.setColumnWidth(i, 10000);
    		Cell cell = row3.createCell(i);
    		cell.setCellValue(m.getTitre() +"\n RS ");
    		cell.setCellStyle(cellStyle);
		
			//Récuperer les élements associés au module
			List<Element> element = elementService.getMatiereByIdModule(m);
			
			//A partir du nombre d'element du module en fusionne les cellules nécessaires
			int y =i+ element.size()+1;
			sheet.addMergedRegion(new CellRangeAddress(3,3,i,y));
			

			//Parcourir la liste des éléments du module associé
			for (int i2 = 0; i2 < element.size(); i2++) {
				
				//Création de cellule de l'elemnt
				sheet.setColumnWidth(i+i2, 9000);
				Cell cell2 = row4.createCell(i+i2);
				cell2.setCellValue(element.get(i2).getNom());
				cell2.setCellStyle(cellStyle);
				
			//Récuprer à partir de la table InscriptionMatiere la liste des notes associé à cet élément	
			List<InscriptionMatiere> imat = inscripmatiereService.getByElement(element.get(i2));	
				
				
				
				//String formula="";
				for(int i4 = 0,i3=5; i4 < imat.size(); i4++,i3++) {
					
					
					Row rowMatiere = sheet.getRow(i3);
					Cell cellMatiere = rowMatiere.createCell(i+i2);
					cellMatiere.setCellValue(imat.get(i4).getNoteFinale());
					cellMatiere.setCellStyle(cellStyle);

					}
				
			 }
			

			//Déclaration du String qui va contenir la formule de calcul de la moyenne de chaque module
			String formula ="";
			//Variable où on va stocker notre formule finale 
			String formula2="";
			
			for(int y2=5;y2<6;y2++) {
				
				Row ro=sheet.getRow(y2);
				
				for(int y1=0;y1<element.size();y1++) {
				
				Cell cx=ro.getCell(y-1-y1-1);
				//Récuperer les coordonné de la cellule sous forme E5
				formula+=CellReference.convertNumToColString(cx.getColumnIndex()) + (cx.getRowIndex() + 1)+"*"+element.get(y1).getCurrentCoefficient()+"+";
				
			   }
				//Eliminer le dernier + ajouter dans la formule
				char[] tab=formula.toCharArray();
				for(int c=0;c<tab.length-1;c++) {
					formula2+=tab[c];
				}
				
				//Créer la cellule moyenne module est affecté sa formule
				Cell cellMoyenne = ro.createCell(y-1);
				cellMoyenne.setCellFormula(formula2);
				cellMoyenne.setCellStyle(cellStyle);
				
				//Variable où on va stocker la formule de la moyenne génerale à partir de la moyenne du module
				String moyenne = CellReference.convertNumToColString(cellMoyenne.getColumnIndex()) + (cellMoyenne.getRowIndex() + 1);
				formulemoyennege+=moyenne+"+";
				
				//Créer la cellule de Validation et établir sa formule "Si"
				String fV="IF("+moyenne+">=12, \"V\", \"NV\")";
				Cell cellValid = ro.createCell(y);
				cellValid.setCellFormula(fV);
				cellValid.setCellStyle(cellStyle);
				
				
			}
			
			//Création des cellules Titre de la moyenne et de la validation
			sheet.setColumnWidth(y-1, 5000);
			Cell cell3 = row4.createCell(y-1);
			cell3.setCellValue("Moyenne");
			cell3.setCellStyle(cellStyle);
			sheet.setColumnWidth(y, 5000);
			Cell cell4 = row4.createCell(y);
			cell4.setCellValue("Validation");
			cell4.setCellStyle(cellStyle);
			
			//Indice par lequel va commencer la prochaine cellule
			i=y+1;
			
		
		}
    	
    	//Eliminer le dernier + ajouter dans la formule de la moyenne génerale et l'affecter a une variable finale
    	String formulemoyennege1="";
    	char[] tab=formulemoyennege.toCharArray();
		for(int c=0;c<tab.length-1;c++) {
			formulemoyennege1+=tab[c];
		}
		
		
        //Créer pou chaque ligne les 2 cellules de moyenne générale et Rang
    	for(int y2=5;y2<6;y2++) {
    		//Récupération de la ligne y2
    		Row ro=sheet.getRow(y2);
    		
    		//Création de la cellule moyenne génerale 
    		Cell cellmoygeneral=ro.createCell(i);
			cellmoygeneral.setCellFormula("ROUND(("+formulemoyennege1+")/"+module.size()+",2)");
			cellmoygeneral.setCellStyle(cellStyle);
			
			//Création de la cellule rang
			String moygeneral= CellReference.convertNumToColString(cellmoygeneral.getColumnIndex()) + (cellmoygeneral.getRowIndex() + 1);
			String pcell=CellReference.convertNumToColString(cellmoygeneral.getColumnIndex())+ (6);
			String dcell=CellReference.convertNumToColString(cellmoygeneral.getColumnIndex())+ (j);
			Cell cellrang=ro.createCell(i+1);
			cellrang.setCellFormula("RANK("+moygeneral+","+pcell+":"+dcell+",0)");
			cellrang.setCellStyle(cellStyle);
			
		}
    	
    	
    	//Création des cellules titres de la moyenne génerale et rang
		Cell cell5 = row3.createCell(i);
		cell5.setCellValue("Moyenne");
		cell5.setCellStyle(cellStyle);
		Cell cell6 = row3.createCell(i+1);
		cell6.setCellValue("Rang");
		cell6.setCellStyle(cellStyle);
		
    	//Affecter la feuille crée à l'objet Excel
		Excel.setSheet(sheet);
		
		//Retourner l'objet Excel
		return Excel;

	}


	


}
