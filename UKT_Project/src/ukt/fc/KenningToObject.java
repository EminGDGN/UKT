package ukt.fc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class KenningToObject {
	JSONParser parser;
	JSONObject graph;
	Graph g;
	
	public KenningToObject() {
		this.parser = new JSONParser();
		this.graph = null;
		this.g = null;
	}
	
	@SuppressWarnings("unchecked")
	public KenningToObject(String jsonFile) throws FileNotFoundException, IOException, ParseException {
		this.parser = new JSONParser();
		
		graph = (JSONObject) ((JSONObject) new JSONParser().parse(new FileReader(jsonFile))).get("graph");
		
		Graph result = new Graph((String)graph.get("id"), (String)graph.get("name"));
		
		JSONArray allNodesJson = (JSONArray) graph.get("nodes");
		
		ArrayList<Node> nodes = new ArrayList<Node>();
         
		for (Object n : allNodesJson) {
			Node tempN = new Node((String)((JSONObject)n).get("id"), (String)((JSONObject)n).get("name"));
			
			ArrayList<Interface> interfaces = new ArrayList<Interface>();
			
			JSONArray allInterfacesJson = (JSONArray) ((JSONObject) n).get("interfaces");
			
			for (Object i : allInterfacesJson) {
				Interface tempI = new Interface((String)((JSONObject)i).get("id"), (String)((JSONObject)i).get("name"));
				tempI.setDirection((String)((JSONObject)i).get("direction"));
				interfaces.add(tempI);
			}
			
			tempN.setInterfaces(interfaces);
			
			/////
			ArrayList<Property> properties = new ArrayList<Property>();
			
			JSONArray allPropertiesJson = (JSONArray) ((JSONObject) n).get("properties");
			
			for (Object i : allPropertiesJson) {
				Property tempP = new Property((String)((JSONObject)i).get("id"), (String)((JSONObject)i).get("name"));
				properties.add(tempP);
			}
			
			tempN.setInterfaces(interfaces);
			
			tempN.setProperties(properties);
			
			nodes.add(tempN);
		}
		result.setNodes(nodes);
		g = result;
	}
	
	public Graph getGraph() {
		return this.g;
	}
}
