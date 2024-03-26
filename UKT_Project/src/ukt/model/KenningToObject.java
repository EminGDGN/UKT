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
import ukt.model.kenningModel.SpecInterface;
import ukt.model.kenningModel.SpecNode;
import ukt.model.kenningModel.SpecProperty;

public class KenningToObject {
	private JSONParser parser; // The parser
	private String graphFilePath; // The file path to the json graph file
	private String specFilePath; // The file path to the json specification file used to create the graph
	
	public KenningToObject() {
		this.parser = new JSONParser();
		this.graphFilePath = "";
		this.specFilePath = "";
	}
	
	public KenningToObject(String graphJsonFile, String specJsonFile) throws FileNotFoundException, IOException, ParseException {
		this.parser = new JSONParser();
		this.graphFilePath = graphJsonFile;
		this.specFilePath = specJsonFile;
	}
	
	public void setGraphPath (String path) {
		this.graphFilePath = path;
	}
	
	public void setSpecPath (String path) {
		this.specFilePath = path;
	}
	
	/**
	 * 
	 * @return the object representation of the json graph
	 * @throws IOException
	 * @throws ParseException
	 */
	public JSONObject getGraphJson () throws IOException, ParseException {
		return (JSONObject) ((JSONObject) this.parser.parse(new FileReader(this.graphFilePath))).get("graph");
	}
	
	/**
	 * 
	 * @return the object representation of the json spec
	 * @throws IOException
	 * @throws ParseException
	 */
	public JSONObject getSpecJson () throws IOException, ParseException {
		return (JSONObject) this.parser.parse(new FileReader(this.specFilePath));
	}
	
	/**
	 * 
	 * @return the graph created based on the json files
	 * @throws Exception
	 */
	public Graph getGraph () throws Exception {
		if (!this.isSpecComplete()) {
			throw new Exception("The specification file is not complete");
		}
		JSONObject jGraph = this.getGraphJson();
		
		ArrayList<SpecNode> specNode = this.getSpecNodes();
		ArrayList<Node> node = this.getNodes();
		
		this.setInterfacesType(specNode, node);
		this.setPropertiesType(specNode, node);
		
		Graph result = new Graph((String)jGraph.get("id"), (String)jGraph.get("name"));
		result.setNodes(node);
		result.setConnection(this.getConnections(result.getInterfaces()));
		return result;
	}
	
	/**
	 * 
	 * @param interfaces, list of all the interfaces of the graph in the json file
	 * @return a list of all the connections of the graph in the json file
	 * @throws IOException
	 * @throws ParseException
	 */
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
	
	/**
	 * 
	 * @return a list of all the nodes of the graph in the json graph file
	 * @throws IOException
	 * @throws ParseException
	 */
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
	
	/**
	 * 
	 * @return a list of all the specifications for nodes in the json spec file
	 * @throws IOException
	 * @throws ParseException
	 */
	public ArrayList<SpecNode> getSpecNodes() throws IOException, ParseException {
		JSONObject jSpec = this.getSpecJson();
		JSONArray allNodesJson = (JSONArray) jSpec.get("nodes");
		ArrayList<SpecNode> result = new ArrayList<SpecNode>();
		
		for (Object n : allNodesJson) {
			SpecNode tempN = new SpecNode((String)((JSONObject)n).get("name"));
			tempN.setInterfaces(this.getSpecInterfaces((JSONObject)n));
			tempN.setProperties(this.getSpecProperties((JSONObject)n));
			result.add(tempN);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param nodes, list of all the specifications for nodes of the graph in the json file
	 * @return a list of all the specifications for interfaces in the json spec file
	 * @throws IOException
	 * @throws ParseException
	 */
	public ArrayList<SpecInterface> getAllSpecInterfaces(ArrayList<SpecNode> nodes) {
		ArrayList<SpecInterface> result = new ArrayList<>();
		
		for (SpecNode n : nodes) {
			result.addAll(n.getSpecInterface());
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param nodes, list of all the specifications for nodes of the graph in the json file
	 * @return a list of all the specifications for properties in the json spec file
	 * @throws IOException
	 * @throws ParseException
	 */
	public ArrayList<SpecProperty> getAllSpecProperties(ArrayList<SpecNode> nodes) {
		ArrayList<SpecProperty> result = new ArrayList<>();
		
		for (SpecNode n : nodes) {
			result.addAll(n.getSpecProperties());
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param node, a specification of a node of the graph in the json file
	 * @return a list of all the specifications of interfaces of node
	 * @throws IOException
	 * @throws ParseException
	 */
	public ArrayList<SpecInterface> getSpecInterfaces(JSONObject node) {
		ArrayList<SpecInterface> result = new ArrayList<SpecInterface>();
		JSONArray allInterfacesJson = (JSONArray) node.get("interfaces");
		
		for (Object i : allInterfacesJson) {
			SpecInterface tempI = new SpecInterface((String)((JSONObject)i).get("name"));
			tempI.setDirection((String)((JSONObject)i).get("direction"));
			tempI.setType((String)((JSONObject)i).get("type"));
			result.add(tempI);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param node, a node of the graph in the json graph file
	 * @return a list of all the interfaces of node
	 */
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
	
	/**
	 * 
	 * @param node, a specification of a node of the graph in the json file
	 * @return a list of all the specifications of properties of node
	 */
	public ArrayList<SpecProperty> getSpecProperties(JSONObject node) {
		ArrayList<SpecProperty> result = new ArrayList<SpecProperty>();
		JSONArray allPropertiesJson = (JSONArray) node.get("properties");
		
		if (allPropertiesJson != null) {
			for (Object i : allPropertiesJson) {
				SpecProperty tempP = new SpecProperty((String)((JSONObject)i).get("name"));
				tempP.setType((String)((JSONObject)i).get("type"));
				result.add(tempP);
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param specNodes, all the nodes specification of the json spec file
	 * @param nodes, all the node of the json graph file
	 * Set the types of every interfaces of every nodes based on the specification
	 * 
	 * @throws Exception
	 */
	private void setInterfacesType (ArrayList<SpecNode> specNodes, ArrayList<Node> nodes) throws Exception {
		ArrayList<SpecInterface> allSpecInterface = this.getAllSpecInterfaces(specNodes);
		for (Node n : nodes) {
			ArrayList <Interface> interfaceN = n.getInterfaces();
			for (Interface i : interfaceN) {
				boolean specPresent = false;
				for (SpecInterface si : allSpecInterface) {
					if (si.getName().equals(i.getName())) {
						specPresent = true;
						i.setType(si.getType());
					}
				}
				if (!specPresent) {
					throw new Exception("Spec non complète");
				}
			}
		}
	}
	
	/**
	 * 
	 * @param specNodes, all the nodes specification of the json spec file
	 * @param nodes, all the node of the json graph file
	 * Set the types of every properties of every nodes based on the specification
	 * 
	 * @throws Exception
	 */
	private void setPropertiesType (ArrayList<SpecNode> specNodes, ArrayList<Node> nodes) throws Exception {
		ArrayList<SpecProperty> allSpecProperties = this.getAllSpecProperties(specNodes);
		for (Node n : nodes) {
			ArrayList <Property> propertiesN = n.getProperties();
			for (Property p : propertiesN) {
				boolean specPresent = false;
				for (SpecProperty sp : allSpecProperties) {
					if (sp.getName().equals(p.getName())) {
						specPresent = true;
						p.setType(sp.getType());
					}
				}
				if (!specPresent) {
					throw new Exception("Spec non complète");
				}
			}
		}
	}
	
	/**
	 * 
	 * @param node, a node of the graph in the json graph file
	 * @return a list of all the properties of node
	 */
	public ArrayList<Property> getProperties(JSONObject node) {
		ArrayList<Property> result = new ArrayList<Property>();
		JSONArray allPropertiesJson = (JSONArray) node.get("properties");
		
		for (Object i : allPropertiesJson) {
			Property tempP = new Property((String)((JSONObject)i).get("id"), (String)((JSONObject)i).get("name"));
			result.add(tempP);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return true if the json spec file is enough to build the graph of the json graph file
	 * @throws Exception
	 */
	public boolean isSpecComplete () throws Exception {
		ArrayList<SpecInterface> allSpecInterface = this.getAllSpecInterfaces(this.getSpecNodes());
		ArrayList<SpecProperty> allSpecProperties = this.getAllSpecProperties(this.getSpecNodes());
		for (Node n : this.getNodes()) {
			ArrayList <Interface> interfaceN = n.getInterfaces();
			for (Interface i : interfaceN) {
				boolean specPresent = false;
				for (SpecInterface si : allSpecInterface) {
					if (si.getName().equals(i.getName())) {
						specPresent = true;
					}
				}
				if (!specPresent) {
					return false;
				}
			}
			
			ArrayList <Property> propertiesN = n.getProperties();
			for (Property p : propertiesN) {
				boolean specPresent = false;
				for (SpecProperty sp : allSpecProperties) {
					if (sp.getName().equals(p.getName())) {
						specPresent = true;
						p.setType(sp.getType());
					}
				}
				if (!specPresent) {
					return false;
				}
			}
		}
		return true;
	}
}
