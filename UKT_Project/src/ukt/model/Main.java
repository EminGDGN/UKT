package ukt.model;

import java.util.ArrayList;


import ukt.model.kenningModel.Graph;
import ukt.model.kenningModel.Node;
import ukt.model.kenningModel.Parameter;

public class Main {

	public static void main(String[] args) throws Exception {
		KenningToObject kto = new KenningToObject("/home/delmedir/Documents/main/cours/polytech/4A/S8/Projet/testTransformation/save.json"
				, "/home/delmedir/Documents/main/cours/polytech/4A/S8/Projet/testTransformation/sample-specification.json");
		
		Graph g = kto.getGraph();
		
		ArrayList<Node> init = g.getInitialNodes();
		
		for (Node n : init) {
			ArrayList<Parameter> params = n.getParameters();
			System.out.println("Paramètre du noeud " + n.getName());
			for (Parameter p : params) {
				p.print();
			}
			System.out.println("----------------------------");
		}
		
		ArrayList<Node> next = new ArrayList<>();
		
		for (Node n : init) {
			next.addAll(g.getSuivant(n));
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~Suivant~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		for (Node n : next) {
			ArrayList<Parameter> params = n.getParameters();
			System.out.println("Paramètre du noeud " + n.getName());
			for (Parameter p : params) {
				p.print();
			}
			System.out.println("----------------------------");
		}
		
		/*ArrayList<Node> test = g.getInitialNodes();
		
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
		}*/
	}
}
