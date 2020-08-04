package logingui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.LoginDatabase;

public class ResearchersUnderPanel extends JPanel {
	private LoginDatabase db;
	ResultSet checkResult;
	List<String> researchers = new ArrayList<String>();
	List<String> researcherWorks = new ArrayList<String>();
	List<String> spare = new ArrayList<String>();
	private SuperiorWorksPanel superiorWorksPanel;
	private ViewFileListener viewFileListener;
	private JButton addResearcher;
	
	public ResearchersUnderPanel(String username) {
		Dimension dim = getPreferredSize();
		dim.width = 380;
		setPreferredSize(dim);
		
		db = new LoginDatabase();
		addResearcher = new JButton("Add more Researchers");
		
		try {
			spare = db.getSpareResearchers();
		} catch (SQLException e3) {
			System.out.println(e3);
		}
		
		try {
			researchers = db.viewResearcherUsernames(username);

		} catch (SQLException e) {
			System.out.println(e);
		}
		
		addResearcher.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new AddResearcher(username,spare);
			}
			
		});


		Border innerBorder = BorderFactory.createTitledBorder("Researchers under your supervision");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		///////////LAYOUT COMPONENTS
		
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		int i=1;
		for(String researcher:researchers) {
			
			JButton viewFilesButton = new JButton("View Research Papers");
			

			gc.gridx = 0;
			gc.gridy++;
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.LINE_END;
			gc.insets = new Insets(0, 0, 0, 5);
			String researcherName=null;
			try {
				researcherName = db.getResearcherName(researcher);
			} catch (SQLException e) {
				System.out.println("Failed getResearcherName in ResearchUnderPanel");
			}
			add(new JLabel(i + ") " + researcherName), gc);
	
			gc.gridx++;
			
			gc.insets = new Insets(0, 0, 0, 0);
			gc.anchor = GridBagConstraints.LINE_START;
			add(viewFilesButton, gc);
			
			viewFilesButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					try {
						researcherWorks = db.viewWorks(researcher);
					} catch (SQLException ev) {
						System.out.println("Failed viewWorks in ResearchUnderPanel");
					}
					
					new ResearcherForSuperior(researcher);

				}
				
			});
			
			i++;
		}
		gc.weighty = 4;
		gc.gridy++;
		gc.gridx=1;
		add(addResearcher,gc);
		

	}
	
/*	void setViewEvent(ViewFileListener viewFileListener) {
		this.viewFileListener = viewFileListener;
	}
*/	


}
