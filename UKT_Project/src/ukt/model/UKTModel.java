package ukt.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ukt.model.cwlModel.Input;
import ukt.model.cwlModel.Workflow;
import ukt.model.kenningModel.Graph;
import ukt.parser.CWLParser;

public class UKTModel {

	private KenningToObject kenningParser;
	private FromKenningObjectsToCWLObjects cwlMaker;
	private CWLParser cwlParser;
	private File cwlFile1, cwlFile2;
	private Workflow currentWorkflow;
	private String lastFilePathWorkflowToRun;
	private HashMap<String, String> inputsValues;

	public UKTModel() {
		kenningParser = new KenningToObject();
		cwlParser = new CWLParser();
	}

	public void addSpecificationFile(File file) {
		String filePath = file.getAbsolutePath();
		this.kenningParser.setSpecPath(filePath);
	}

	public void addGraphFile(File file) {
		String filePath = file.getAbsolutePath();
		this.kenningParser.setGraphPath(filePath);
	}

	public boolean isKenningConfigOK() {
		try {
			return kenningParser.isSpecComplete();
		} catch (Exception e) {
			return false;
		}
	}

	public String getCWLConverted() throws Exception {
		Graph g = kenningParser.getGraph();
		cwlMaker = new FromKenningObjectsToCWLObjects(g);
		Workflow w = cwlMaker.getCWLWorkflow();
		String convertedStringGraph = w.toString();
		String folderPath = System.getProperty("user.home") + File.separator + "UKT";
	    String fileName = "graph_"+Clock.systemDefaultZone().millis();
	    String filePath = folderPath + File.separator + fileName;
	    lastFilePathWorkflowToRun = filePath;
	    File folder = new File(folderPath);
	    
	    if (!folder.exists()) {
	    	folder.mkdirs();
	    }
	    
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	        writer.write(convertedStringGraph);
	    } catch (IOException e) {
	    	throw new Exception();
	    }
		return convertedStringGraph;
	}
	
	public void addBaseCommandOneFile(File selectedFile) {
		cwlFile1 = selectedFile;
	}

	public void addBaseCommandTwoFile(File selectedFile) {
		cwlFile2 = selectedFile;
	}

	public boolean isBaseCommandOneOK() {
		return !cwlParser.isWorkflow(cwlFile1);
	}
	
	public boolean isBaseCommandTwoOK() {
		return !cwlParser.isWorkflow(cwlFile2);
	}
	
	public String mergeCWL() throws Exception {
	    Workflow w = cwlParser.merge(cwlFile1, cwlFile2);
	    currentWorkflow = w;
	    inputsValues = new HashMap<>();
	    String mergedStringWorkflow = w.toString();
	    String folderPath = System.getProperty("user.home") + File.separator + "UKT";
	    String fileName = cwlParser.getNameWithoutExtansion(cwlFile1.getName()) + "_" + cwlFile2.getName();
	    String filePath = folderPath + File.separator + fileName;
	    lastFilePathWorkflowToRun = filePath;
	    File folder = new File(folderPath);
	    
	    if (!folder.exists()) {
	    	folder.mkdirs();
	    }
	    
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	        writer.write(mergedStringWorkflow);
	    } catch (IOException e) {
	    	throw new Exception();
	    }
	    
	    Path sourceFile1 = Paths.get(cwlFile1.getPath());
	    Path sourceFile2 = Paths.get(cwlFile2.getPath());
	    Path targetFolder = Paths.get(folderPath);
	    
	    Path targetFile1 = targetFolder.resolve(cwlFile1.getName());
	    Path targetFile2 = targetFolder.resolve(cwlFile2.getName());
	    
	    Files.copy(sourceFile1, targetFile1, StandardCopyOption.REPLACE_EXISTING);
	    Files.copy(sourceFile2, targetFile2, StandardCopyOption.REPLACE_EXISTING);

	    return mergedStringWorkflow;
	}
	
	public boolean doesCurrentWorkflowNeedsInputs() {
		return currentWorkflow.getInputs().size()!=0;
	}
	
	public ArrayList<Input> currentWorkflowInputs() {
		return currentWorkflow.getInputs();
	}
	
	public void addInputValue(String key, String value) {
		inputsValues.put(key, value);
	}
	
	public String getCWLResult() {
		try {
			String commandLine = "cwltool "+lastFilePathWorkflowToRun;
			
			for(Map.Entry<String, String> entry : inputsValues.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    commandLine += " --"+key+"="+value;
			}

			java.lang.Process p = Runtime.getRuntime().exec(commandLine);
			
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";    
			String result = "";
			while ((line = reader.readLine())!= null) {
				result+=line;
			}
			return result;
		}
		catch (Exception e) {
			return null;
		}
	}
	
}
