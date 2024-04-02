package ukt.view.components.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UKTHomePanel extends JPanel {

    public UKTHomePanel() {
        add(new JLabel("Welcome! Go to the File menu and choose between: Create CWL Workflow or Convert Kenning graph."));
    }
}