package logingui;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import model.LoginDatabase;

public class ResearcherForSuperior extends JFrame {
	
	private ResearchWorkSuperior researcherWork; 
	private LoginDatabase db;
	private String name;
	private TextPanel textPanel;
	public ResearcherForSuperior(String username) {
		
		super("Researcher Information under Superior");
		setLayout(new BorderLayout());
		
		researcherWork = new ResearchWorkSuperior(username);
		//researchPaper = new JTextArea();
		//researchPaper.setVisible(true);
		textPanel = new TextPanel();

		
		db = new LoginDatabase();
		
		try {
			name = db.getResearcherName(username);
		} catch (SQLException e) {
			System.out.println("FAILED TO GET RESEARCHER NAME");
		}

		researcherWork.setViewEvent(new ViewFileListener() {
			public void viewFileOccured(String text,String fileName) {
				textPanel.set(text);
			}
			
		});
		
		add(researcherWork,BorderLayout.WEST);
		add(textPanel,BorderLayout.EAST);
		add(new JLabel("                                            "+name+ "'s Research Papers"),BorderLayout.NORTH);

		
		setSize(1200, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

}
