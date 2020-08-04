package logingui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.LoginDatabase;

public class AddResearcher extends JFrame {
	
	private JComboBox users;
	private JButton add;
	private LoginDatabase db;
	Dictionary userNames = new Hashtable();
	
	public AddResearcher(String superior,List<String> spare) {
		super("Upload File");
		setLayout(new GridBagLayout());
		
		users = new JComboBox();
		add = new JButton("Add");
		db = new LoginDatabase();
		
		DefaultComboBoxModel userModel = new DefaultComboBoxModel();
		
		for(String user:spare) {
			String name="";
			try {
				name = db.getResearcherName(user);
			} catch (SQLException e) {
				System.out.println(e);
			}
		
			userModel.addElement(name);
			//System.out.println(year);
			userNames.put(name, user);
		}
		
		users.setModel(userModel);
//		users.setSelectedIndex(0);
		users.setEditable(false);
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String researcherName = (String)users.getSelectedItem();
				String researcher = (String)userNames.get(researcherName);
				try {
					db.addResearcher(superior,researcher);
				} catch (SQLException e1) {
					System.out.println("Couldn't execute addResearcher in AddResearcher");
				}
				new SuperiorFrame(superior);
			}
			
		});
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		//gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Select User:"), gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		//gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(users, gc);
		
		////////////Second row ///////////////////////////////////
		
		gc.gridx = 1;
		gc.gridy = 1;
		//gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(add, gc);
		
		setSize(250, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
