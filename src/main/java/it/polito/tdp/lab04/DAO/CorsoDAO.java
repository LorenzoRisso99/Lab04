package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				
				
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(c);
				
			}

			conn.close();		
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un corso, ottengo il codice insegnamento
	 */
	public String getCorso(String corso) {
		
		final String sql = "SELECT c.codins " + "FROM corso c " + "WHERE c.nome = ?";
		String codins = null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, corso);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				codins = rs.getString("codins");
			}

			conn.close();

			return codins;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(String corso) {
		
		final String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS " + "FROM corso c, iscrizione i, studente s "
			            	+ "WHERE i.codins = c.codins AND i.codins = ? AND i.matricola = s.matricola";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, corso);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("cds");

				System.out.println(matricola + " " + cognome + " " + nome + " " + cds);

				Studente s = new Studente(matricola, cognome, nome, cds);
				studenti.add(s);
			}

			conn.close();

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}
	
	
	public boolean isIscritto(String nomeCorso, int matricola) {
		
		final String sql = "SELECT s.nome " + "FROM iscrizione i, studente s, corso c "
				+ "WHERE i.codins = c.codins AND s.matricola = i.matricola AND c.nome = ? AND s.matricola = ?";

		boolean flag = false;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, nomeCorso);
			st.setInt(2, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String nome = rs.getString("nome");

				if (nome != null) {
					flag = true;
				}
			}

			conn.close();

			return flag;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

}
