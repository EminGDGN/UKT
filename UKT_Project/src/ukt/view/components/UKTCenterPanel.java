package ukt.view.components;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ukt.controller.Panel;
import ukt.controller.UKTController;
import ukt.view.components.panels.UKTHomePanel;
import ukt.view.components.panels.UKTKenningPanel;
import ukt.view.components.panels.UKTWorkflowPanel;

public class UKTCenterPanel extends JPanel {

	private UKTController controller;
	private CardLayout cardLayout;
	
	// Panels
	private UKTHomePanel homePanel;
	private UKTKenningPanel kenningPanel;
	private UKTWorkflowPanel workflowPanel;

	public UKTCenterPanel(UKTController controller) {
		super();
		
		this.controller = controller;
		
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		
		homePanel = new UKTHomePanel();
		kenningPanel = new UKTKenningPanel(controller);
		workflowPanel = new UKTWorkflowPanel(controller);
		
		this.add(Panel.HOME_PANEL.getName(),homePanel);
		this.add(Panel.KENNING_PANEL.getName(),kenningPanel);
		this.add(Panel.WORKFLOW_PANEL.getName(),workflowPanel);
	}

	public void setPanelVisible(Panel p) {
		this.cardLayout.show(this, p.getName());
	}
	
	public UKTKenningPanel getKenningPanel() {
		return kenningPanel;
	}
	
	public UKTWorkflowPanel getWorkflowPanel() {
		return workflowPanel;
	}

}