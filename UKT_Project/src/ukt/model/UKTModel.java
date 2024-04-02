package ukt.model;

import java.io.File;

import ukt.model.cwlModel.Workflow;
import ukt.model.kenningModel.Graph;

public class UKTModel {

	private KenningToObject kenningParser;
	private FromKenningObjectsToCWLObjects cwlMaker;

	public UKTModel() {
		kenningParser = new KenningToObject();
		cwlMaker = new FromKenningObjectsToCWLObjects();
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
		// TODO Auto-generated method stub
	}

	public void addBaseCommandTwoFile(File selectedFile) {
		// TODO Auto-generated method stub
	}

	public boolean isBaseCommandOK() {
		// TODO Auto-generated method stub
		return true;
	}

}
