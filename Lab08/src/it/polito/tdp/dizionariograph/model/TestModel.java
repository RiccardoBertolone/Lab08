package it.polito.tdp.dizionariograph.model;

import java.util.List;

import org.jgrapht.Graphs;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.createGraphJava(2);
		System.out.println(String.format("**Grafo creato**\n"));
//		System.out.println(model.getGrafo());
		
//		List<String> vicini = model.displayNeighbours("casa");
//		System.out.println("Neighbours di casa: " + vicini + "\n");
//		
		System.out.println("Cerco il vertice con grado massimo...");
		System.out.println(model.findMaxDegree() +"\n"+ Graphs.neighborListOf(model.getGrafo(), model.findMaxDegree()));
	}

}
