package logingui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class Toolbar extends JPanel {
	private JButton saveButton;
	private SaveListener listener;
	
	public Toolbar() {
		saveButton = new JButton("Save/Update");
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null) {
					listener.saveOccured();
				}
			}
			
		});
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(saveButton);

	}
	
	public void setSaveListener(SaveListener listener) {
		this.listener = listener;
	}
	
}