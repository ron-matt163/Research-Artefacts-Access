package logingui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	
	private JButton loginButton;
	private JButton registerButton;
	private JTextField userField;
	private JPasswordField passField;
	private FormListener formListener;
	private RegisterEvent registerEvent;
	
	public FormPanel() {
		
		Dimension dim = getPreferredSize();
		dim.width = 300;
		setPreferredSize(dim);
		
		loginButton = new JButton("Login");
		registerButton = new JButton("Register");
		userField = new JTextField(10);
		passField = new JPasswordField(10);
		
		loginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(formListener != null) {
					//System.out.println("clicked");
					String username = userField.getText();
					char[] passwd = passField.getPassword();
					//String password = passwd.toString();
					formListener.userData(username, new String(passwd));
				}
			}
			
			
		});
		
		registerButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(registerEvent != null) {
					new RegisterFrame();
					registerEvent.setInvisible();
					
				}
			}
			
			
		});
		
		
		Border innerBorder = BorderFactory.createTitledBorder("Login Form");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		///////////LAYOUT COMPONENTS
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Username:"), gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(userField, gc);
		
		////////////Second row ///////////////////////////////////
		
	//	gc.weightx = 1;
	//	gc.weighty = 1;
		
		gc.gridy = 1;
		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Password:"), gc);
		
		gc.gridy = 1;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(passField, gc);
		
		//////
		
		gc.weightx = 1;
		gc.weighty = 2.0;
		
		gc.gridy = 2;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(loginButton, gc);

		//////
		
		gc.gridy = 3;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(new JLabel("Don't have an account?"), gc);

		
		gc.gridy = 3;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(registerButton, gc);


	}
	
	public void setRegisterEvent(RegisterEvent registerEvent) {
		this.registerEvent = registerEvent;
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
	

//	public void layout() {

		



}
