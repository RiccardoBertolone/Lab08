package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	private Graph<String, DefaultEdge> grafo ;
	private Set<String> parole;
	private WordDAO dao = new WordDAO();
	

	public void createGraph(int numeroLettere) {
		grafo = new SimpleGraph<>(DefaultEdge.class);
		parole = dao.getAllWordsFixedLength(numeroLettere);
		
		Graphs.addAllVertices(grafo, parole); // aggiungo i vertici
		
		for (String s : grafo.vertexSet()) { //aggiungo gli archi
			Set<String> vicini = dao.getVicini(s);
			for (String s2 : vicini) {
				grafo.addEdge(s, s2);
			}
		}
		
	}

	public List<String> displayNeighbours(String parolaInserita) {

		return Graphs.neighborListOf(grafo, parolaInserita); 
		
	}

	public String findMaxDegree() {
		int max = 0;
		String result = "";
		for (String s : grafo.vertexSet()) {
			int dim = Graphs.neighborListOf(grafo, s).size();
			if (dim>max) {
				max = dim;
				result = s ;
			}
		}
		return result;
	}

	public Graph<String, DefaultEdge> getGrafo() {
		return grafo;
	}

	public void createGraphJava(int nLettere) {
		
		grafo = new SimpleGraph<>(DefaultEdge.class);
		parole = dao.getAllWordsFixedLength(nLettere);
		
		Graphs.addAllVertices(grafo, parole); // aggiungo i vertici
		
		for (String s : grafo.vertexSet()) { //aggiungo gli archi
			Set<String> vicini = this.getVicini(s);
			for (String s2 : vicini) {
				grafo.addEdge(s, s2);
			}
		}
		
	}

	private Set<String> getVicini(String s) {
		Set<String> vicini = new HashSet<String>() ;
		for (String parola : grafo.vertexSet()) {
			if (this.eSimile(parola, s))
				vicini.add(parola);
		}
		return vicini;
	}

	private boolean eSimile(String parola, String s) {
		int counter = 0;
		
		for(int i=0; i<s.length(); i++) {
			if (parola.charAt(i) != s.charAt(i))
				counter++;
		}		
		
		if (counter!=1)
			return false;				
		return true;
	}
	
	
	
}
