package ukt.model;

import java.io.File;

public class UKTModel {
	
	public UKTModel() {
		
	}
	
	public void processGraphFile(File file) {
        System.out.println("File selected: " + file.getAbsolutePath());
    }
}
