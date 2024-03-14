package ukt.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ukt.model.kenningModel.Connection;
import ukt.model.kenningModel.Graph;
import ukt.model.kenningModel.Interface;
import ukt.model.kenningModel.Node;
import ukt.model.kenningModel.Property;

public class KenningToObject {
	private JSONParser parser;
	private String filePath;
	
	public KenningToObject() {
		this.parser = new JSONParser();
		this.filePath = "";
	}
	
	public KenningToObject(String jsonFile) throws FileNotFoundException, IOException, ParseException {
		this.parser = new JSONParser();
		this.filePath = jsonFile;
	}
	
	public void setPath (String path) {
		this.filePath = path;
	}
	
	public JSONObject getGraphJson () throws IOException, ParseException {
		return (JSONObject) ((JSONObject) this.parser.parse(new FileReader(this.filePath))).get("graph");
	}
	
	public Graph getGraph () throws IOException, ParseException {
		JSONObject jGraph = this.getGraphJson();
		Graph result = new Graph((String)jGraph.get("id"), (String)jGraph.get("name"));
		result.setNodes(getNodes());
		result.setConnection(getConnections(result.getInterfaces()));
		return result;
	}
	
	public ArrayList<Connection> getConnections(ArrayList<Interface> interfaces) throws IOException, ParseException {
		JSONObject jGraph = this.getGraphJson();
		JSONArray allConnectionsJson = (JSONArray) jGraph.get("connections");
		ArrayList<Connection> result = new ArrayList<Connection>();
		
		for (Object n : allConnectionsJson) {
			String idC = (String)((JSONObject)n).get("id");
			String idF = (String)((JSONObject)n).get("from");
			String idT = (String)((JSONObject)n).get("to");
			
			Interface interfaceFrom = null;
			Interface interfaceTo = null;
			
			for (Interface i : interfaces) {
				if (i.getId().equals(idF)) {
					interfaceFrom = i;
				} else if (i.getId().equals(idT)) {
					interfaceTo = i;
				}
			}
			
			Connection tempC = new Connection(idC, interfaceFrom, interfaceTo);
			result.add(tempC);
		}
		
		return result;
	}
	
	public ArrayList<Node> getNodes() throws IOException, ParseException {
		JSONObject jGraph = this.getGraphJson();
		JSONArray allNodesJson = (JSONArray) jGraph.get("nodes");
		ArrayList<Node> result = new ArrayList<Node>();
		
		for (Object n : allNodesJson) {
			Node tempN = new Node((String)((JSONObject)n).get("id"), (String)((JSONObject)n).get("name"));
			tempN.setInterfaces(this.getInterfaces((JSONObject)n));
			tempN.setProperties(this.getProperties((JSONObject)n));
			result.add(tempN);
		}
		
		return result;
	}
	
	public ArrayList<Interface> getInterfaces(JSONObject node) {
		ArrayList<Interface> result = new ArrayList<Interface>();
		JSONArray allInterfacesJson = (JSONArray) node.get("interfaces");
		
		for (Object i : allInterfacesJson) {
			Interface tempI = new Interface((String)((JSONObject)i).get("id"), (String)((JSONObject)i).get("name"));
			tempI.setDirection((String)((JSONObject)i).get("direction"));
			result.add(tempI);
		}
		
		return result;
	}
	
	public ArrayList<Property> getProperties(JSONObject node) {
		ArrayList<Property> result = new ArrayList<Property>();
		JSONArray allPropertiesJson = (JSONArray) node.get("properties");
		
		for (Object i : allPropertiesJson) {
			Property tempP = new Property((String)((JSONObject)i).get("id"), (String)((JSONObject)i).get("name"));
			result.add(tempP);
		}
		
		return result;
	}
}
