package logingui;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.LoginDatabase;

public class MainFrame extends JFrame {
	
	private FormPanel formPanel;
	private LoginDatabase db;
	
	public MainFrame() {
		super("Login");
		
		setLayout(new BorderLayout());
		
		formPanel = new FormPanel();
		db = new LoginDatabase();
		
		formPanel.setFormListener(new FormListener() {
			public void userData(String username, String password) {
				System.out.println(username + " " + password);
				try {
					db.connect();
				} catch (Exception e) {
					System.out.println("Connection to database failed "+ e);
				}
				try {
					if(db.check(username, password)) {
						JOptionPane.showMessageDialog(null,"Login Successful");
						setVisible(false);
						if(db.checkPosition(username,password)=="Researcher") {
							new ResearcherFrame(username);
						}
						else if(db.checkPosition(username,password)=="Superior") {
							new SuperiorFrame(username);
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"Login Failed\nIncorrect username/password");					}
				} catch (SQLException e) {
					System.out.println("ERROR IN CHECKING LOGIN");
				}
				
			}
		});
		
		formPanel.setRegisterEvent(new RegisterEvent() {

			public void setInvisible() {
				setVisible(false);
			}
			
		});
		
		add(formPanel,BorderLayout.CENTER);
	
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);			
	}
	
	
}
