package logingui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;


public class TextPanel extends JPanel {
	
	private JTextArea textArea;
	private String fileText;
	private Toolbar toolbar;
	private String fileName;
	
	public TextPanel() {
		textArea = new JTextArea();
		toolbar = new Toolbar();
		Dimension dim = getPreferredSize();
		dim.width = 760;
		setPreferredSize(dim);
		setVisible(false);
		
		Border innerBorder = BorderFactory.createTitledBorder("Selected research paper (EDITABLE ONLY BY RESEARCHERS)");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		
	}
	
	public void appendText(String text) {
		this.fileText = text;
		setVisible(true);
		textArea.append(text);
	}
	public void set(String text) {
		this.fileText = text;
		setVisible(true);
		textArea.setText(text);
		textArea.setEditable(false);
	}
	
	public void load(String text,String filename) {
		this.fileName = fileName;
		this.fileText = text;
		add(toolbar,BorderLayout.NORTH);
		setVisible(true);
		textArea.setText(text);
		toolbar.setSaveListener(new SaveListener() {
			public void saveOccured() {
				String savedText = textArea.getText();
				FileWriter fw;
				try {
					fw = new FileWriter(filename);
			        fw.write(savedText);    
			        fw.close(); 
				} catch (IOException e) {
					System.out.println("Couldn't create file in load in textpanel");
				}    

			}
			
		});
		
	}
	

}