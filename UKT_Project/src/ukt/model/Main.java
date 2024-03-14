package ukt.model;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		KenningToObject kto = new KenningToObject("/home/delmedir/Documents/main/cours/polytech/4A/S8/Projet/testJQ/save.json");
		kto.getGraph().print();
	}
}
