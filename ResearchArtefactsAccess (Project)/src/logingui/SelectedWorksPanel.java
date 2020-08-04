package logingui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.LoginDatabase;

public class SelectedWorksPanel extends JPanel {
	
	private LoginDatabase db;
	List<String> works = new ArrayList<String>();
	String filename = "";
	String text = "";
	private ViewFileListener listener;
	
	public SelectedWorksPanel(String year,String topic) {
		Dimension dim = getPreferredSize();
		dim.width = 400;
		setPreferredSize(dim);
		
		db = new LoginDatabase();
		
		try {
			works = db.papersOfYear(year,topic);

		} catch (SQLException e1) {
			System.out.println("Failed to call papersOfYear in selectedPanel");
		}
		
		Border innerBorder = BorderFactory.createTitledBorder("Published in "+year+"      "+"Topic: "+topic);
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		/////////////////////////////////
		
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
			gc.anchor = GridBagConstraints.LINE_START;
			add(viewFileButton, gc);
			
			viewFileButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if(listener != null) {

						try {
							filename="D:\\"+db.getPaper(work);
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
		gc.gridy++;
		gc.gridx=1;
		add(new JLabel(""),gc);
		
	}
	
	public void setViewEvent(ViewFileListener listener) {
		this.listener = listener;
	}

}
