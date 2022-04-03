package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getTuttiGliStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				System.out.println(matricola + " " + cognome + " " + nome + " " + CDS);

				// Crea un nuovo JAVA Bean Corso
				Studente s = new Studente(matricola,cognome,nome,CDS);
				
				
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				studenti.add(s);
				
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public List<String> getCorsiDiQuelloStudente(int matricola) {

		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM studente s, iscrizione i, corso c "
				+ "WHERE s.matricola = i.matricola AND c.codins = i.codins AND s.matricola = ?";

		List<String> corsiDiQuelloStudente = new LinkedList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);


				// Crea un nuovo JAVA Bean Corso
				Corso c = new Corso(codins,numeroCrediti,nome,periodoDidattico);
				
				
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsiDiQuelloStudente.add(c.toString());
				
			}

			conn.close();
			System.out.println(corsiDiQuelloStudente);
			return corsiDiQuelloStudente;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

}
