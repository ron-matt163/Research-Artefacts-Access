package logingui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class RegisterFrame extends JFrame {
	private RegisterPanel registerPanel;
	
	public RegisterFrame() {
		
		super("Register");
		setLayout(new BorderLayout());
		
		registerPanel = new RegisterPanel();
		
		add(registerPanel,BorderLayout.CENTER);
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}


}
