package ukt.test;

import java.util.ArrayList;

import ukt.model.cwlModel.Step;
import ukt.model.cwlModel.Types;
import ukt.parser.CWLParser;

public class testMergeCWL {

	public static void main(String[] args) {
		
		CWLParser parser = new CWLParser("/home/utilisateur/Downloads");
		ArrayList<String> files = new ArrayList<>();
		files.add("uppercase.cwl");
		files.add("echo.cwl");
		Step temp = parser.parse("echo");
		System.out.println(temp + "\n\n");
		
		temp = parser.parse("uppercase");
		System.out.println(temp);
	}
}
