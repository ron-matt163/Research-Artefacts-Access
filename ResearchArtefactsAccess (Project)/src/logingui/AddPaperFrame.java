package logingui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.LoginDatabase;

public class AddPaperFrame extends JFrame {
	
	private AddPaperPanel panel;
	private JTextArea textArea;
	private JScrollPane text;
	private JButton submit;
	private JPanel east;
	private JPanel west;
	private LoginDatabase db;

	public AddPaperFrame(String username) {

		super("Add a Research Paper");
		setLayout(new BorderLayout());
		
		panel = new AddPaperPanel();
		textArea = new JTextArea();
		text = new JScrollPane(textArea);
		submit = new JButton("Submit");
		text.setSize(700, 500);
		east = new JPanel();
		west = new JPanel();
		db = new LoginDatabase();
		
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String content = textArea.getText();
				String title = panel.getTitle();
				String filename = panel.getFilename();
				String year = panel.getYear();
				String topic = panel.getTopic();
				String fileLoc = "D:\\"+filename;
				FileWriter fw;
				try {
					fw = new FileWriter(fileLoc);
			        fw.write(content);    
			        fw.close(); 
			        db.addPaper(username,title,filename,year,topic);
				} catch (IOException | SQLException ev) {
					System.out.println("Couldn't create file in submit action listener");
					System.out.println("Or failed to execute addPaper");
				}  
				setVisible(false);
				new ResearcherFrame(username);
			}
	
		});

		add(panel,BorderLayout.NORTH);
		add(text,BorderLayout.CENTER);
		add(submit,BorderLayout.SOUTH);
		add(east,BorderLayout.EAST);
		add(west,BorderLayout.WEST);

		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	

		
	}
}
