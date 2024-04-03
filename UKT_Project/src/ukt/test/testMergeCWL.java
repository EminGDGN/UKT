package ukt.test;

import java.io.File;
import java.util.ArrayList;

import ukt.model.cwlModel.Workflow;
import ukt.parser.CWLParser;

public class testMergeCWL {

	public static void main(String[] args) {
		String path = "/home/utilisateur/Downloads";
		CWLParser parser = new CWLParser();
		ArrayList<File> files = new ArrayList<>();
		
		File f1 = new File(path + "/echo.cwl");
		File f2 = new File(path + "/hello_world.cwl");
		
		Workflow test = parser.merge(f2,f1);
		System.out.println(test.getName() + "\n" + test);
		
	}
}
