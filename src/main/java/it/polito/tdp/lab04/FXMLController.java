package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<String> cmbBox;

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	txtArea.clear();
		if (txtMatricola.getText().equals("")) {
			txtArea.setText("Inserisci un matricola");
			return;
		}
		int s = Integer.parseInt(txtMatricola.getText());
		List<String> r = model.getCorsiDiQuelloStudente(s);
		if(r.size() == 0) {
			txtArea.setText("Matricola non esistente");
			return;
		}
		for (String k : r) {
			txtArea.appendText(k + "\n");
		}

    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	
    	txtArea.clear();
		String s = cmbBox.getValue();
		// Fai if
		if (s != null) {
			txtArea.appendText(s);
			List<Studente> r = model.studentiDiQuelCorso(s);
			for (Studente stud : r) {
				txtArea.appendText(stud.toString() + "\n");
			}
		} else {
			txtArea.appendText("Seleziona un corso");
		}
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	txtArea.clear();
		if (txtMatricola.getText().equals("")) {
			txtArea.setText("Inserisci un matricola");
			return;
		}
		if(model.isIscritto(cmbBox.getValue(), Integer.parseInt(txtMatricola.getText())) == true) {
			txtArea.appendText("Studente gia iscritto");
		}
		else {
			txtArea.appendText("Ancora non iscritto");
		}

    }
    
    @FXML
    void doCompleta(ActionEvent event) {
    	
    	txtArea.clear();
		if (txtMatricola.getText().equals("")) {
			txtArea.setText("Inserisci un matricola");
			return;
		}
		if (model.completa(Integer.parseInt(txtMatricola.getText())) == null) {
			txtArea.setText("Matricola non esistente");
			return;
		}
		txtCognome.setText(model.completa(Integer.parseInt(txtMatricola.getText())).getCognome());
		txtNome.setText(model.completa(Integer.parseInt(txtMatricola.getText())).getNome());

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtArea.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	cmbBox.setValue(null);
    }
    
    
    public void setModel(Model model) {
		this.model = model;
		
		List<String> lista = model.getNomeCorsi();
		lista.add("Corso Generale");
		cmbBox.getItems().addAll(lista);
		
	}

	@FXML
    void initialize() {
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbBox != null : "fx:id=\"cmbBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
    }

}
