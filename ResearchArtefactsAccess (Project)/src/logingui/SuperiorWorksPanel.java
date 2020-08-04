package logingui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.LoginDatabase;

public class SuperiorWorksPanel extends JPanel {
	
	private LoginDatabase db;
	List<String> researcherWorks = new ArrayList<String>();
	
	public SuperiorWorksPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 485;
		setPreferredSize(dim);
		
		db = new LoginDatabase();
		
		Border innerBorder = BorderFactory.createTitledBorder("Work done by researcher selected");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		
		
		/////////////////////////////////////////////////////////


	}
	
	public void showContents(List<String> works) {
		
		int i=1;
		for(String work:works) {

			setLayout(new GridBagLayout());

			GridBagConstraints gc = new GridBagConstraints();
			gc.gridx = 0;
			gc.gridy++;
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.LINE_START;
			//gc.insets = new Insets(0, 0, 0, 5);
			add(new JLabel(i + ") " + work), gc);
			
			System.out.println("got here");
			gc.gridx++;
			
			//gc.insets = new Insets(0, 0, 0, 0);
			gc.anchor = GridBagConstraints.LINE_START;
			add(new JButton("View this file"), gc);
			
			
			
			i++;
		}
		
	}
	
}
