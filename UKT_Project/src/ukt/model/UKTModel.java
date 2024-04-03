package ukt.model;

import java.io.File;

import ukt.model.cwlModel.Workflow;
import ukt.model.kenningModel.Graph;
import ukt.parser.CWLParser;

public class UKTModel {

	private KenningToObject kenningParser;
	private FromKenningObjectsToCWLObjects cwlMaker;
	private CWLParser cwlParser;
	private File cwlFile1, cwlFile2;

	public UKTModel() {
		kenningParser = new KenningToObject();
		cwlMaker = new FromKenningObjectsToCWLObjects();
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
		Workflow w = cwlMaker.translate(g);
		return w.toString();
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
	
	public String mergeCWL() {
		Workflow w = cwlParser.merge(cwlFile1, cwlFile2);
		return w.toString();
	}
	
}
