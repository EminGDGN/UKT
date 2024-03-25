package ukt.controller;

import java.io.File;

import ukt.model.UKTModel;
import ukt.view.UKTView;

public class UKTController {

	private UKTModel model;
	private UKTView view;

	public UKTController() {
		this.model = new UKTModel();
		this.view = new UKTView(this);

		view.startInterface();
	}

	public void handleCommand(Command cmd) {
		if (cmd != null) {
			switch (cmd) {
			case CREATE_WORKFLOW:
				this.createWorkflow();
				break;
			case ADD_PROCESS_WORKFLOW:
				this.addProcessToWorkflow();
				break;
			case REMOVE_PROCESS_WORKFLOW:
				this.removeProcessToWorkflow();
				break;
			case ADD_SPECIFICATION_FILE:
				this.addSpecFile();
				break;
			case DISPLAY_KENNING_PANEL:
				this.displayKenningPanel();
				break;
			case CONVERT_KENNING_TO_CWL:
				this.convertKenningToCWL();
				break;
			case RUN_CWL:
				this.runCWL();
				break;
			case ADD_GRAPH_FILE:
				this.addGraphFile();
				break;
			case EXIT_APPLICATION:
				this.exitApplication();
			default:
				break;
			}
		}
	}
	
	private void createWorkflow() {
		view.setPanel(Panel.WORKFLOW_PANEL);
	}
	
	private void addProcessToWorkflow() {
		File selectedFile = view.showFileChooser("Select a graph", FileType.CWL);
		if (selectedFile != null) {
			
		}
	}
	
	private void removeProcessToWorkflow() {
	}
	
	private void displayKenningPanel() {
		view.setPanel(Panel.KENNING_PANEL);
	}

	private void addGraphFile() {
		File selectedFile = view.showFileChooser("Select a graph", FileType.JSON);
		if (selectedFile != null) {
			
		}
	}
	
	private void addSpecFile() {
		File selectedFile = view.showFileChooser("Select specifications", FileType.JSON);
		if (selectedFile != null) {
			
		}
	}
	
	private void convertKenningToCWL() {
	}
	
	private void runCWL() {
	}


	private void exitApplication() {
		System.exit(0);
	}

}
