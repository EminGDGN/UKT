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
			case LOAD_GRAPH:
				this.loadGraph();
				break;
			case EXIT_APPLICATION:
				this.exitApplication();
			default:
				break;
			}
		}
	}
	
	private void createWorkflow() {
		String filename = view.showOptionPane("Enter name of new CWL workflow");
	    if (filename != null && !filename.trim().isEmpty() && !filename.contains(".cwl") && !filename.contains(".CWL")) {
	        view.setPanel(Panel.WORKFLOW_PANEL);
	    } else {
	    	view.displayErrorMessage("Enter a valid name");
	    }
	}
	
	private void addProcessToWorkflow() {
		File selectedFile = view.showFileChooser("Select a graph", FileType.CWL);
		if (selectedFile != null) {
			
		}
	}
	
	private void removeProcessToWorkflow() {
	}

	private void loadGraph() {
		File selectedFile = view.showFileChooser("Select a graph", FileType.JSON);
		if (selectedFile != null) {
			view.setPanel(Panel.KENNING_PANEL);
			model.processGraphFile(selectedFile);
		}
	}


	private void exitApplication() {
		System.exit(0);
	}

}
