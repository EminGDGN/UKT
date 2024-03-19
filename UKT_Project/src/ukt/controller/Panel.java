package ukt.controller;

public enum Panel {

	HOME_PANEL("HomePanel"), 
	WORKFLOW_PANEL("WorkflowPanel"), 
	KENNING_PANEL("KenningPanel");

	private final String name;

	Panel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
