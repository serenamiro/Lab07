package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<Out> parziale;
	List<Out> partenza;
	private List<Out> bestSoluzione = null;
	private int numAffected = 0;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Out> getOutByNercId(int nercId){
		return podao.selecData(nercId);
	}

	public List<Out> calcolaOutages(int nercId, int X, int Y){
		parziale = new ArrayList<>();
		partenza = this.getOutByNercId(nercId);
		
		cerca(parziale, 0, partenza, X, Y);
		return bestSoluzione;
	}

	private void cerca(List<Out> parziale, int livello, List<Out> partenza, int X, int Y) {
		// casi terminali
		if(calcolaHours(parziale) > Y || calcolaYears(parziale)> X) {
			return;
		}
		
		if(calcolaHours(parziale)==Y && calcolaYears(parziale)==X) {
			int affected = calcolaAffected(parziale);
			if(affected > numAffected) {
				numAffected = affected;
				bestSoluzione = new ArrayList<Out>(parziale);
			}
		}
		
		if(livello == partenza.size()) {
			return;
		}
		
		parziale.add(partenza.get(livello));
		cerca(parziale, livello+1, partenza, X, Y);
		parziale.remove(partenza.get(livello));
		
		cerca(parziale, livello+1, partenza, X, Y);	
	}

	private int calcolaYears(List<Out> parziale) {
		int counter = 0;
		if(parziale.size() != 0) {
			int annoRif = parziale.get(0).getYear();
			counter = 1;
			for(int i = 1; i<parziale.size(); i++) {
				if(parziale.get(i).getYear() != annoRif) {
					counter++;
					annoRif = parziale.get(i).getYear();
				}
			}
		}
		return counter;
	}

	public int calcolaHours(List<Out> parziale) {
		int numOre = 0;
		for(Out o : parziale) {
			numOre += o.getHours();
		}
		return numOre;
	}

	private int calcolaAffected(List<Out> parziale) {
		int totale = 0;
		for(Out o : parziale) {
			totale += o.getAffected();
		}
		return totale;
	}

	public int getNumAffected() {
		return numAffected;
	}

	public void setNumAffected(int numAffected) {
		this.numAffected = numAffected;
	}
	
	
	
}
