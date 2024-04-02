package ukt.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import ukt.model.KenningToObject;
import ukt.model.cwlModel.Workflow;
import ukt.model.kenningModel.Graph;

public class testTranslate {

	public static void main(String[] args) throws Exception {
		KenningToObject instance = new KenningToObject("save.json", "sample-specification.json");
		Graph graph = instance.getGraph();
		Workflow workflow = ukt.model.FromKenningObjectsToCWLObjects.translate(graph);
		
		String cwl = workflow.toString();
		System.out.println(cwl);
		String path = "echo_upper.cwl";
		
		try {
			Files.writeString(Paths.get(path), cwl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
