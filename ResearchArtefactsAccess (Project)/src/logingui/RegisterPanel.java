package logingui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.LoginDatabase;

public class RegisterPanel extends JPanel {
	
	private JTextField firstName;
	private JTextField lastName;
	private JTextField username;
	private JPasswordField password;
	private JComboBox category;
	private JButton submit;
	private LoginDatabase db;
	
	public RegisterPanel() {
		
		Dimension dim = getPreferredSize();
		dim.width = 380;
		setPreferredSize(dim);
		
		firstName = new JTextField(15);
		lastName = new JTextField(15);
		username = new JTextField(10);
		password = new JPasswordField(10);
		category = new JComboBox();
		submit = new JButton("Submit");
		db = new LoginDatabase();
		
		DefaultComboBoxModel catModel = new DefaultComboBoxModel();
		catModel.addElement("Researcher");
		catModel.addElement("Superior");
		
		category.setModel(catModel);
		category.setSelectedIndex(0);
		category.setEditable(false);
		
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String fn = firstName.getText();
				String ln = lastName.getText();
				String un = username.getText();
				String pwd = new String(password.getPassword());
				String cat = (String)category.getSelectedItem();
				System.out.println(fn+ln+un+pwd+cat);
				try {
					db.registerUser(un,pwd,cat,fn,ln);
					JOptionPane.showMessageDialog(null,"Registration Successful");
				} catch (SQLException e1) {
					System.out.println(e1);
					JOptionPane.showMessageDialog(null,"Registration Failed");
				}
				new MainFrame();
			}
			
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Register");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("First Name:"), gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(firstName, gc);
		
		////////////Second row ///////////////////////////////////
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Last Name:"), gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(lastName, gc);
		
		////////////Second row ///////////////////////////////////

		gc.gridy = 2;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Category:"), gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(category, gc);
		
		////////////Second row ///////////////////////////////////

		gc.gridy = 3;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Username:"), gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(username, gc);
		
		///////////////////////////////
		

		gc.gridy = 4;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Password:"), gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(password, gc);
		///////////////////////////////
		
		gc.weighty = 10;
		gc.gridx = 1;
		gc.gridy = 5;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(submit, gc);

	}

}
