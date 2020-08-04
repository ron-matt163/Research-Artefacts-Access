package logingui;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.LoginDatabase;

public class ResearcherFrame extends JFrame {
	
	private ResearcherWorkPanel researcherWork; 
	private LoginDatabase db;
	private String name;
	private TextPanel textPanel;
	
	public ResearcherFrame(String username) {
		
		super("Researcher Information");
		setLayout(new BorderLayout());
		
		researcherWork = new ResearcherWorkPanel(username);
		textPanel = new TextPanel();
		
		db = new LoginDatabase();
		
		try {
			name = db.getResearcherName(username);
		} catch (SQLException e) {
			System.out.println("FAILED TO GET RESEARCHER NAME");
		}

		researcherWork.setViewEvent(new ViewFileListener() {
			public void viewFileOccured(String work,String fileName) {
				textPanel.load(work,fileName);
			}
			
		});
		
		add(textPanel,BorderLayout.EAST);
		add(researcherWork,BorderLayout.WEST);
		add(new JLabel("                                            "+name+ "'s Research Papers"),BorderLayout.NORTH);

		setSize(1200, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

}
