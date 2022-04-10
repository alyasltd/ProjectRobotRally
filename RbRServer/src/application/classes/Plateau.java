package application.classes;

import java.util.List;
import java.util.ArrayList;

public class Plateau {
	
	public Plateau() {
		setCaseList(new ArrayList<Case>());}
	
	

	// attributs 
	private List<Case> CaseList;
	
	
	//getter et setter 
	
	public List<Case> getCaseList() {
		return CaseList;
	}

	public void setCaseList(List<Case> caseList) {
		CaseList = caseList;
		}
	
	
	// m�thodes
	
	public void add(Case maCase) {
		CaseList.add(maCase);
	}
	
	public void remove(Case maCase) {
		CaseList.remove(maCase);
	}
	
}
