package logingui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.LoginDatabase;

public class ResearcherWorkPanel extends JPanel {
	
	private LoginDatabase db;
	List<String> works = new ArrayList<String>();
	private ViewFileListener listener;
	String filename="C:\\Users\\ryant\\Desktop\\towatch.txt";
	String text = "";
	private JButton addPapers;
	private JButton uploadPapers;
	private JButton checkOthers;

	public ResearcherWorkPanel(String username) {
		Dimension dim = getPreferredSize();
		dim.width = 400;
		setPreferredSize(dim);
		
		db = new LoginDatabase();
		addPapers = new JButton("Add Research Papers");
		uploadPapers = new JButton("Upload Research Papers");
		checkOthers = new JButton("View Works of Others");
		
		try {
			if(db.advisorApproval(username) == false) {
				checkOthers.setEnabled(false);
			}
		} catch (SQLException e3) {
			System.out.println("failed to execute advisorApproval");
		}
		
		
		try {
			works = db.viewWorks(username);

		} catch (SQLException e) {
			System.out.println("could't execute viewworks()");
		}

		addPapers.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new AddPaperFrame(username);
			}
			
		});
		
		uploadPapers.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//System.out.println("upload button clicked");
				/*

			    */
				
				new UploadFrame(username);
			}

		});
		
		checkOthers.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
					new ViewOthersFrame();
			}
			
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Papers published");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		///////////LAYOUT COMPONENTS
		
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		int i=1;
		for(String work:works) {

			gc.gridx = 0;
			gc.gridy++;
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.LINE_START;
			//gc.insets = new Insets(0, 0, 0, 5);
			add(new JLabel(i + ") " + work), gc);
			
			gc.gridx++;
			
			JButton viewFileButton = new JButton("View this file");
			
			//gc.insets = new Insets(0, 0, 0, 0);
			gc.anchor = GridBagConstraints.LINE_END;
			add(viewFileButton, gc);
			
			viewFileButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if(listener != null) {
						

						try {
							filename="D:\\"+db.getPaper(work,username);
						} catch (SQLException e2) {
							System.out.println("Failed to call getpaper");
						}


					    try {
							text = new String(Files.readAllBytes(Paths.get(filename)));
						} catch (IOException e1) {
							System.out.println("couldn't load file");
						}
						listener.viewFileOccured(text,filename);
					}
				}
				
			});
			
			i++;
		}
		
		gc.weighty=4;
		gc.weightx=3;
		gc.gridy++;
		
		gc.gridx=0;
		gc.anchor = GridBagConstraints.LINE_START;
		add(uploadPapers,gc);
		
		gc.gridx=1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(addPapers,gc);
		
		gc.weighty=0.8;
		gc.gridy++;
		
		try {
			if(db.advisorApproval(username) == false)
			{
				gc.gridx=0;
				gc.anchor = GridBagConstraints.LINE_START;
				add(new JLabel("No permission to view other work"),gc);
			}
		} catch (SQLException e1) {
			System.out.println("didnt call in gridbag");
		}
	
		gc.gridx=1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(checkOthers,gc);
		
	}
	
	public void setViewEvent(ViewFileListener listener) {
		this.listener = listener;
	}

}
