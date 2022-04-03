package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private StudenteDAO studenteDAO;
	private CorsoDAO corsoDAO;
	//private Studente s;
	
	public Model() {
		this.studenteDAO = new StudenteDAO();
		this.corsoDAO = new CorsoDAO();
		//List<Studente> studenti = s.getMatricola();
	}
	
	public List<Corso> getTuttiICorsi() {
		return this.corsoDAO.getTuttiICorsi();
	}
	
	//Per popolare la tendina
	public List<String> getNomeCorsi() {
		List<String> l = new ArrayList<String>();
		for(Corso d : corsoDAO.getTuttiICorsi()) {
			l.add(d.getNome());
		}
		return l;
	}
	
	public List<Studente> getTuttiGliStudenti() {
		return this.studenteDAO.getTuttiGliStudenti();
	}
	
	public Studente completa(int matricola) {
		for (Studente s : getTuttiGliStudenti()) {
			if (s.getMatricola() == matricola) {
				return s;
			}
		}
		return null;
	}

	public List<Studente> studentiDiQuelCorso(String corso) {
		List<Studente> l = new ArrayList<>();
		l = corsoDAO.getStudentiIscrittiAlCorso(corsoDAO.getCorso(corso));
		return l;
	}

	public List<String> getCorsiDiQuelloStudente(int matricola) {
		List<String> l = new ArrayList<>();
		l = studenteDAO.getCorsiDiQuelloStudente(matricola);
		return l;
	}

	public boolean isIscritto(String nomeCorso, int matricola) {
		return corsoDAO.isIscritto(nomeCorso, matricola);
	}

}
