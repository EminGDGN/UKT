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

	private void loadGraph() {
		File selectedFile = view.showFileChooser("Select a graph", FileType.JSON);
		if (selectedFile != null) {
			model.processGraphFile(selectedFile);
		}
	}


	private void exitApplication() {
		System.exit(0);
	}

}
