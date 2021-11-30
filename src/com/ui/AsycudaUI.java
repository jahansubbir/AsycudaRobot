package com.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;



import com.bll.AutomationManager;
import com.bll.ExcelManager;
import com.models.Egm;


import main.Program;

public class AsycudaUI extends JFrame {
	/**
		 * 
		 */
	Map<String, LinkedList<Egm>>  egmList;
	private static final long serialVersionUID = 1L;
	private JLabel excelFileLabel, xmlFolderLabel, titleLabel;

	private JLabel getExcelFileLabel() {
		excelFileLabel = new JLabel("Excel Template File");
		excelFileLabel.setBounds(130, 130, 150, 30);
		excelFileLabel.setForeground(new Color(82, 106, 255));
		return excelFileLabel;
	}

	private JLabel getXmlFolderLabel() {
		xmlFolderLabel = new JLabel("XML Folder");
		xmlFolderLabel.setBounds(excelFileLabel.getX(), excelFileLabel.getY() + excelFileLabel.getHeight() + 20,
				excelFileLabel.getWidth(), excelFileLabel.getHeight());
		xmlFolderLabel.setForeground(excelFileLabel.getForeground());
		return xmlFolderLabel;
	}

	private JLabel getTitleLabel() {
		titleLabel = new JLabel("ASYCUDA WORLD AUTOMATION 1.0");
		titleLabel.setBounds(130, 20, excelFileLabel.getWidth(), excelFileLabel.getHeight());
		titleLabel.setForeground(excelFileLabel.getForeground());
		return titleLabel;
	}

	private JTextField getExcelPathTextField() {
		excelPathTextField = new JTextField(300);
		excelPathTextField.setBounds(excelFileLabel.getX() + excelFileLabel.getWidth(), excelFileLabel.getY(), 200, 30);
		excelPathTextField.setForeground(new Color(89, 103, 255));
		return excelPathTextField;
	}

	private JTextField getXmlFolderPathTextField() {

		xmlFolderPathTextField = new JTextField();
		xmlFolderPathTextField.setBounds(excelPathTextField.getX(), xmlFolderLabel.getY(),
				excelPathTextField.getWidth(), excelPathTextField.getHeight());
		xmlFolderPathTextField.setForeground(new Color(89, 103, 255));
		return xmlFolderPathTextField;
	}

	private JButton getBrowseExcelButton() {
		browseExcelButton = new JButton("Browse");
		browseExcelButton.setBounds(excelPathTextField.getX() + excelPathTextField.getWidth() + 30,
				excelPathTextField.getY(), 100, 30);
		browseExcelButton.setBackground(new Color(39, 59, 69));
		browseExcelButton.setForeground(new Color(255 - 39, 255 - 59, 255 - 69));
		browseExcelButton.setFocusPainted(false);
		/*
		 * browseExcelButton.addActionListener(ActionHandler.onBrowseButtonClick(
		 * bodyPanel, excelPathTextField, "Excel"));
		 */
		browseExcelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				onBrowseButtonClicked("excel", excelPathTextField);
			}
		});
		;
		return browseExcelButton;
	}

	private JButton getBrowseXmlFolderButton() {
		browseXmlFolderButton = new JButton("Browse");
		browseXmlFolderButton.setBounds(browseExcelButton.getX(), xmlFolderPathTextField.getY(),
				browseExcelButton.getWidth(), browseExcelButton.getHeight());
		browseXmlFolderButton.setFocusPainted(false);
		browseXmlFolderButton.setBackground(browseExcelButton.getBackground());
		browseXmlFolderButton.setForeground(browseExcelButton.getForeground());
		browseXmlFolderButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				onBrowseButtonClicked(null, xmlFolderPathTextField);
			}
		});
		return browseXmlFolderButton;
	}

	protected JButton getAutomateButton() {
		automateButton = new JButton("Automate");
		automateButton.setBounds(xmlFolderPathTextField.getX(),
				xmlFolderPathTextField.getY() + xmlFolderPathTextField.getHeight() + 30,
				xmlFolderPathTextField.getWidth(), 40);
		automateButton.setFocusPainted(false);
		automateButton.setBackground(browseExcelButton.getBackground());
		automateButton.setForeground(browseExcelButton.getForeground());
		automateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// onAutomateButtonClick();
				String filePath = excelPathTextField.getText();
				String xmlFolder = xmlFolderPathTextField.getText() + "\\";
				Program.excelFilePath = filePath;
				
				try {
					egmList = excelManager.GetEgmList(filePath, "EGM");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(new JPanel(), e1.getMessage());
				}

				setState(ICONIFIED);
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						automationManager.Automate(egmList, xmlFolder, filePath);
						JPanel panel=new JPanel();
						panel.setBounds(0, 0, 400, 400);
						
						JOptionPane.showMessageDialog(panel, "Completed task. Check The template for details");
					}
				});

				thread.start();

			}
		});
		return automateButton;
	}

	private JTable getTable() {
		return table;
	}

	private JTextField excelPathTextField, xmlFolderPathTextField;
	private JButton browseExcelButton, browseXmlFolderButton;
	private JButton automateButton;
	private JTable table;

	private JPanel bodyPanel;

	private static long getSerialversionuid() {
		return serialVersionUID;
	}

	private JPanel getBodyPanel() {

		bodyPanel = new JPanel(null);
		bodyPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
		bodyPanel.setBackground(new Color(245, 250, 255));

		return bodyPanel;
	}

	private void addControlsToPanel() {
		bodyPanel.add(getExcelFileLabel());
		bodyPanel.add(getExcelPathTextField());
		bodyPanel.add(getBrowseExcelButton());

		bodyPanel.add(getXmlFolderLabel());
		bodyPanel.add(getXmlFolderPathTextField());
		bodyPanel.add(getBrowseXmlFolderButton());

		bodyPanel.add(getAutomateButton());
	}

	private final ExcelManager excelManager;
	private final AutomationManager automationManager;

	public AsycudaUI(ExcelManager excelManager, AutomationManager automationManager,String title) {

		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(getBodyPanel());

		setTitle(title);
		setBounds(0, 0, 800, 600);
		add(getBodyPanel());
		addControlsToPanel();
		// setContentPane(this);
		setVisible(true);
		// automateButtonClick();
		this.excelManager = excelManager;
		this.automationManager = automationManager;
	}

	private void onBrowseButtonClicked(String fileType, Component component) {
		JFileChooser excelChooser = new JFileChooser();
		if (fileType != null) {
			FileFilter filter = new FileNameExtensionFilter("Excel file", "xls", "xlsx");
			excelChooser.setFileFilter(filter);
		} else {
			excelChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}
		// excelChooser.addChoosableFileFilter(filter);
		int i = excelChooser.showOpenDialog(this.bodyPanel);
		if (i == JFileChooser.APPROVE_OPTION) {
			JTextField field = (JTextField) component;
			field.setText(excelChooser.getSelectedFile().getPath());
		}
	}

	public void onAutomateButtonClick() {
		String filePath = getExcelPathTextField().getText();

		/*
		 * ArrayList<Egm> egmList = excelManager.GetEgmList(filePath, "EGM");
		 * this.setState(ICONIFIED); automationManager.Automate(egmList,
		 * xmlFolderPathTextField.getText());
		 */
	}
}
