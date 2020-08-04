package logingui;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.LoginDatabase;

public class SuperiorFrame extends JFrame {
	
	private ResearchersUnderPanel researchersUnderPanel;
	ResultSet result;
	private LoginDatabase db;
	private String name;
	private SuperiorWorksPanel superiorWorksPanel;
	private ResearcherWorkPanel researcherWorkPanel;
	List<String> works = new ArrayList<String>();
	
	public SuperiorFrame(String username) {
			
		super("Superior Information");
		setLayout(new BorderLayout());
		
		researchersUnderPanel = new ResearchersUnderPanel(username);
	//	superiorWorksPanel = new SuperiorWorksPanel();
		db = new LoginDatabase();
		
		try {
			name = db.getSuperiorName(username);
			System.out.println(name);
		} catch (SQLException e) {
			System.out.println("FAILED TO GET SUPERIOR NAME");
		}

		add(researchersUnderPanel,BorderLayout.WEST);
	//	add(superiorWorksPanel,BorderLayout.EAST);
		add(new JLabel("                                               Hello "+name+" !"),BorderLayout.NORTH);
		
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
}
