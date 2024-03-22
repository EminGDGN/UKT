package ukt.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import ukt.model.kenningModel.Graph;
import ukt.model.kenningModel.Node;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		KenningToObject kto = new KenningToObject("/home/delmedir/Documents/main/cours/polytech/4A/S8/Projet/testJQ/save.json");
		//kto.getGraph().print();
		
		Graph g = kto.getGraph();
		
		ArrayList<Node> test = g.getInitialNodes();
		
		ArrayList<Node> ksui = g.getSuivant(test.get(2));
		
		System.out.println("Les neux initiaux sont les suivants : ");
		for (Node n : test) {
			System.out.println(" - " + n.getName());
		}
		System.out.println("Les noeuds suivant les initiaux sont : ");
		for (Node n : ksui) {
			System.out.println(" - " + n.getName());
		}
		
		ArrayList<Node> pred = g.getPrecedent(g.getSuivant(test.get(0)).get(0));
		
		System.out.println("Les noeuds précédent sont : ");
		for (Node n : pred) {
			System.out.println(" - " + n.getName());
		}
	}
}
