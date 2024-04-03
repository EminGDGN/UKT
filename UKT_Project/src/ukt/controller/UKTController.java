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
			case ADD_BASECOMMAND1_WORKFLOW:
				this.addBaseCommandOneToWorkflow();
				break;
			case ADD_BASECOMMAND2_WORKFLOW:
				this.addBaseCommandTwoToWorkflow();
				break;
			case RESET_KENNING_VIEW:
				this.initKenningView();
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
			case MERGE_BASECOMMAND:
				this.mergeBaseCommand();
				break;
			case RESET_WORKFLOW_VIEW:
				this.initWorkflowView();
				break;
			case RUN_WORKFLOW:
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
	
	private void initWorkflowView() {
		view.setWorkflowInitBC1();
		view.setWorkflowInitBC2();
		view.setWorkflowInitCwlText();
		view.setWorkflowResetButtonEnable(false);
		view.setWorkflowMergeButtonEnable(false);
		view.setWorkflowRunButtonEnable(false);
		view.setWorkflowInitCwlResult();
	}

	private void mergeBaseCommand() {
		try {
			String cwlMerged = model.mergeCWL();
			view.setWorkflowCwlText(cwlMerged);
			view.setWorkflowRunButtonEnable(true);
		} catch (Exception e) {
			view.displayErrorMessage("Error has occurred during merge");
			initWorkflowView();
		}
	}

	private void createWorkflow() {
		view.setPanel(Panel.WORKFLOW_PANEL);
		view.setWorkflowInitBC1();
		view.setWorkflowInitBC2();
		view.setWorkflowInitCwlText();
		view.setWorkflowResetButtonEnable(false);
		view.setWorkflowMergeButtonEnable(false);
		view.setWorkflowRunButtonEnable(false);
		view.setWorkflowInitCwlResult();
	}
	
	private void addBaseCommandOneToWorkflow() {
		File selectedFile = view.showFileChooser("Select a CWL file", FileType.CWL);
		if (selectedFile != null) {
			model.addBaseCommandOneFile(selectedFile);
			if (model.isBaseCommandOneOK()) {
				view.setWorkflowBC1FileName(selectedFile.getPath());
				view.setWorkflowResetButtonEnable(true);
			} else {
				view.displayErrorMessage("The imported CWL file is not of type CommandLineTool");
				initWorkflowView();
			}
		}
	}
	
	private void addBaseCommandTwoToWorkflow() {
		File selectedFile = view.showFileChooser("Select a CWL file", FileType.CWL);
		if (selectedFile != null) {
			model.addBaseCommandTwoFile(selectedFile);
			if (model.isBaseCommandTwoOK()) {
				view.setWorkflowMergeButtonEnable(true);
				view.setWorkflowBC2FileName(selectedFile.getPath());
			} else {
				view.displayErrorMessage("The imported CWL file is not of type CommandLineTool or ExpressionTool.");
				initWorkflowView();
			}
		}
	}
		
	private void displayKenningPanel() {
		view.setPanel(Panel.KENNING_PANEL);
		view.setKenningInitSpec();
		view.setKenningInitGraph();
		view.setKenningAddGraphButtonEnable(false);
		view.setKenningConvertButtonEnable(false);
		view.setKenningInitCwlGraph();
	}
	
	private void initKenningView() {
		view.setKenningInitSpec();
		view.setKenningInitGraph();
		view.setKenningInitCwlGraph();
		view.setKenningResetButtonEnable(false);
		view.setKenningAddGraphButtonEnable(false);
		view.setKenningConvertButtonEnable(false);
	}

	private void addGraphFile() {
		File selectedFile = view.showFileChooser("Select a graph", FileType.JSON);
		if (selectedFile != null) {
			model.addGraphFile(selectedFile);
			if (model.isKenningConfigOK()) {
				view.setKenningConvertButtonEnable(true);
				view.setKenningGraphFileName(selectedFile.getPath());
			} else {
				view.displayErrorMessage("Kenning configuration is not correct");
				view.setKenningInitSpec();
				view.setKenningInitGraph();
				view.setKenningAddGraphButtonEnable(false);
			}
		}
	}
	
	private void addSpecFile() {
		File selectedFile = view.showFileChooser("Select specifications", FileType.JSON);
		if (selectedFile != null) {
			model.addSpecificationFile(selectedFile);
			view.setKenningAddGraphButtonEnable(true);
			view.setKenningResetButtonEnable(true);
			view.setKenningSpecFileName(selectedFile.getPath());
		}
	}
	
	private void convertKenningToCWL() {
		try {
			String stringCwl = model.getCWLConverted();
			view.setKenningCwlGraphText(stringCwl);
		} catch (Exception e) {
			view.displayErrorMessage("Error has occurred during conversion");
			view.setKenningInitCwlGraph();
		}
	}
	
	private void runCWL() {
		String res = model.getCWLResult();
		if (res!=null) {
			view.setWorkflowCwlResult("Result:\n\n"+res);
		} else {
			view.displayErrorMessage("Impossible to run workflow.");
		}
	}

	private void exitApplication() {
		System.exit(0);
	}

}
