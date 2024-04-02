package ukt.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import ukt.controller.FileType;
import ukt.controller.Panel;
import ukt.controller.UKTController;
import ukt.view.components.UKTCenterPanel;
import ukt.view.components.UKTMenuBar;

public class UKTView extends JFrame {

    /* Controller */
    private UKTController controller;

    /* UI Components */
    private JFileChooser fileChooser;
    private UKTMenuBar menuBar;
    private UKTCenterPanel centerPanel;

    /* Constructor */
    public UKTView(UKTController controller) {
        super("UKT"); // title of the window
        this.controller = controller;
        this.initComponents();
    }

    private void initComponents() {
        fileChooser = new JFileChooser();
        menuBar = new UKTMenuBar(controller);
        centerPanel = new UKTCenterPanel(controller);

        // Set menu bar
        setJMenuBar(menuBar);

        // Add center panel
        add(centerPanel, BorderLayout.CENTER);

    }

    public void startInterface() {
        // Set window size
        setSize(1000, 500);

        // Make the window appear in the middle of the screen
        setLocationRelativeTo(null);

        // Exit on closing window
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set first panel
        setPanel(Panel.HOME_PANEL);

        // Set the window visible
        setVisible(true);
    }

    public void setPanel(Panel p) {
        centerPanel.setPanelVisible(p);
        centerPanel.repaint();
    }

    public File showFileChooser(String title, FileType type) {
        fileChooser.setDialogTitle(title);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter(type.getDisplayName() + " files", type.getExtension()));

        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }

    public String showOptionPane(String title) {
        String userSelection = JOptionPane.showInputDialog(null, title);
        if (userSelection != null) {
            return userSelection;
        } else {
            return null;
        }
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void setKenningResetButtonEnable(boolean b) {
    	centerPanel.getKenningPanel().setResetButtonEnable(b);
    }
    
    public void setKenningAddGraphButtonEnable(boolean b) {
    	centerPanel.getKenningPanel().setGraphButtonEnable(b);
    }
    
    public void setKenningConvertButtonEnable(boolean b) {
    	centerPanel.getKenningPanel().setConvertButtonEnable(b);
    }
    
    public void setKenningSpecFileName(String s) {
    	centerPanel.getKenningPanel().activateSpecFileName(s);
    }
    
    public void setKenningGraphFileName(String s) {
    	centerPanel.getKenningPanel().activateGraphFileName(s);
    }
    
    public void setKenningInitSpec() {
    	centerPanel.getKenningPanel().initSpecFileName();
    }
    
    public void setKenningInitGraph() {
    	centerPanel.getKenningPanel().initGraphFileName();
    }
    
    public void setKenningInitCwlGraph() {
    	centerPanel.getKenningPanel().initCwlGraphTextArea();
    }
    
    public void setKenningCwlGraphText(String s) {
    	centerPanel.getKenningPanel().setCwlGraphText(s);
    }
        
    public void setWorkflowResetButtonEnable(boolean b) {
    	centerPanel.getWorkflowPanel().setResetButtonEnable(b);
    }
        
    public void setWorkflowMergeButtonEnable(boolean b) {
    	centerPanel.getWorkflowPanel().setMergeButtonEnable(b);
    }
    
    public void setWorkflowBC1FileName(String s) {
    	centerPanel.getWorkflowPanel().activateBC1FileName(s);
    }
    
    public void setWorkflowBC2FileName(String s) {
    	centerPanel.getWorkflowPanel().activateBC2FileName(s);
    }
    
    public void setWorkflowInitBC1() {
    	centerPanel.getWorkflowPanel().initBC1FileName();
    }
    
    public void setWorkflowInitBC2() {
    	centerPanel.getWorkflowPanel().initBC2FileName();
    }
    
    public void setWorkflowInitCwlText() {
    	centerPanel.getWorkflowPanel().initCwlText();
    }
    
    public void setWorkflowCwlText(String s) {
    	centerPanel.getWorkflowPanel().setCwlText(s);
    }
    
    public void setWorkflowInitCwlResult() {
    	centerPanel.getWorkflowPanel().initCwlResult();
    }
    
    public void setWorkflowCwlResult(String s) {
    	centerPanel.getWorkflowPanel().setCwlResult(s);
    }
    
}