package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.jgrapht.Graphs;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class DizionarioGraphController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNLettere;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnGeneraGrafo;

    @FXML
    private Button btnTrovaVicini;

    @FXML
    private Button btnTrovaGradoMax;

    @FXML
    private Button btnReset;
    
    @FXML
    private TextArea txtOuput;
    
    private Model model;

    @FXML
    void doGeneraGrafo(ActionEvent event) {
    	txtOuput.clear();
    	String s = txtNLettere.getText() ;
    	int nLettere = 0;
    	try {
    		nLettere = Integer.parseInt(s);
    	} 
    	catch(NumberFormatException e) {
    		txtOuput.setText("Inserisci un valore valido per il numero di caratteri.");
    	}
    	
    	if(nLettere == 0) {
    		txtOuput.setText("Inserisci un numero di caratteri diverso da 0");
    		return;
    	}
    	
//    	model.createGraph(nLettere);
    	model.createGraphJava(nLettere);
    	txtOuput.setText("Creato un grafo di "+this.model.getGrafo().vertexSet().size()+" vertici e "+this.model.getGrafo().edgeSet().size()+" archi");

    }

    @FXML
    void doGeneraVicini(ActionEvent event) {
    	txtOuput.clear();
    	String input = txtParola.getText();
    	List<String> result = this.model.displayNeighbours(input);
    	String output = "Vicini di "+input+"\n";
    	
    	for (String s : result) {
    		output+=s+"\n";
    	}
    	output=output.trim();
    	txtOuput.setText(output);
    	
    }

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void doTrovaGradoMax(ActionEvent event) {
    	txtOuput.clear();
    	String result = this.model.findMaxDegree();
    	
    	String output = "Parola di grado massimo ("+Graphs.neighborListOf(this.model.getGrafo(), result).size()+"): "+result+"\n";
    	
    	for (String s : Graphs.neighborListOf(this.model.getGrafo(), result)) {
    		output+=s+"\n";
    	}
    	output=output.trim();
    	txtOuput.setText(output);
    }

    @FXML
    void initialize() {
        assert txtNLettere != null : "fx:id=\"txtNLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaGradoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtOuput != null : "fx:id=\"txtOuput\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }

	public void setModel(Model model) {
		
		this.model=model;
		
	}
}
