package logingui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.LoginDatabase;

public class UploadFrame extends JFrame {
	
	private JTextField title;
	private JTextField topic;
	private JTextField year;
	private JButton choose;
	private LoginDatabase db;
	
	public UploadFrame(String researcher) {
		super("Upload File");
		setLayout(new GridBagLayout());
		
		title = new JTextField(20);
		topic = new JTextField(15);
		year = new JTextField(7);
		choose = new JButton("Choose file");
		db = new LoginDatabase();
		
		choose.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser(new File("D:\\")); 
			    FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files","txt");
			    j.setFileFilter(filter);
			    int returnVal = j.showOpenDialog(UploadFrame.this);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    //   System.out.println("You chose to open this file: " +
			      //      j.getSelectedFile());
			       String filename = (j.getSelectedFile().getName());
			       String titleFile = title.getText();
			       String yearPub = year.getText();
			       String topicSel = topic.getText();
			     //  System.out.println(topic);
			     //  System.out.println(filename);
			       try {
					db.addPaper(researcher, titleFile, filename,yearPub,topicSel);
				} catch (SQLException e1) {
					System.out.println("Failed to execute addPaper in uploadFrame");
				}
			       setVisible(false);
			       new ResearcherFrame(researcher);
			    }
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
		add(new JLabel("Enter title:"), gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		//gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(title, gc);
		
		////////////Second row ///////////////////////////////////
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		//gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Topic:"), gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		//gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(topic, gc);
   ////////////Second row ///////////////////////////////////
		
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		//gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Year:"), gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		//gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(year, gc);
/////////////////////////////////////////////////////
		gc.weighty = 10;
		
		gc.gridy = 3;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(choose, gc);
		
		//////
		
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
