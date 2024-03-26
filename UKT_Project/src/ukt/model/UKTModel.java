package ukt.model;

import java.io.File;

public class UKTModel {
	
	private KenningToObject kenningParser;
	
	public UKTModel() {
		kenningParser = new KenningToObject();
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
}
