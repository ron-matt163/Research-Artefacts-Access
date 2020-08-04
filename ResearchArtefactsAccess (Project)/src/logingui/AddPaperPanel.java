package logingui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class AddPaperPanel extends JPanel{
	
	private JTextField title;
	private JTextField filename;
	private JTextField year;
	private JTextField topic;
	private JButton submit;
	
	public AddPaperPanel() {
		
		Dimension dim = getPreferredSize();
		dim.width = 500;
		dim.height = 200;
		setPreferredSize(dim);
		
		title = new JTextField(15);
		filename = new JTextField(10);
		year = new JTextField(5);
		topic = new JTextField(10);
		submit = new JButton("Submit");

		Border innerBorder = BorderFactory.createTitledBorder("Add new paper details");
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
		add(new JLabel("Title:"), gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(title, gc);
		
		////////////Second row ///////////////////////////////////
		
	//	gc.weightx = 1;
	//	gc.weighty = 1;
		
		gc.gridy = 1;
		gc.gridx = 0;
		//gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Filename:"), gc);
		
		gc.gridy = 1;
		gc.gridx = 1;
		//gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(filename, gc);

		/////////////
		////////////Second row ///////////////////////////////////
		
	//	gc.weightx = 1;
	//	gc.weighty = 1;
		
		gc.gridy = 1;
		gc.gridx = 0;
		//gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Filename:"), gc);
		
		gc.gridy = 1;
		gc.gridx = 1;
		//gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(filename, gc);

		/////////////
		gc.gridy = 2;
		gc.gridx = 0;
		//gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Year:"), gc);
		
		gc.gridy = 2;
		gc.gridx = 1;
		//gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(year, gc);

		/////////////
		gc.gridy = 3;
		gc.gridx = 0;
		//gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Topic:"), gc);
		
		gc.gridy = 3;
		gc.gridx = 1;
		//gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(topic, gc);

		/////////////
		gc.gridy = 4;
		gc.gridx = 0;
		//gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_START;
		add(new JLabel("Type in the contents:"), gc);
			
	}
	
	public String getTitle() {
		String text;
		text = title.getText();
		return text;
	}

	public String getFilename() {
		String text;
		text = filename.getText();
		return text;
	}
	
	public String getYear() {
		String text;
		text = year.getText();
		return text;
	}
	
	public String getTopic() {
		String text;
		text = topic.getText();
		return text;
	}
}
