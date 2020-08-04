package logingui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.LoginDatabase;

public class ViewOthersPanel extends JPanel {
	
	private JComboBox yearList;
	private JButton search;
	List<String> years = new ArrayList<String>();
	List<String> topics = new ArrayList<String>();
	private LoginDatabase db;
	private JComboBox topicList;
	
	public ViewOthersPanel() {
		
		Dimension dim = getPreferredSize();
		dim.width = 400;
		setPreferredSize(dim);
		
		yearList = new JComboBox();
		search = new JButton("Search");
		db = new LoginDatabase();
		topicList = new JComboBox();
		
		DefaultComboBoxModel yearModel = new DefaultComboBoxModel();
		try {
			years = db.getYears();
		} catch (SQLException e) {
			System.out.println("Could't execute getYears in othersPanel");
		}
		
		for(String year:years) {
			yearModel.addElement(year);
			//System.out.println(year);
		}
		yearModel.addElement("All");
		
		yearList.setModel(yearModel);
		yearList.setSelectedIndex(0);
		yearList.setEditable(false);
		
		///////////////
		
		DefaultComboBoxModel topicModel = new DefaultComboBoxModel();
		try {
			topics = db.getTopics();
		} catch (SQLException e) {
			System.out.println("Could't execute getYears in othersPanel");
		}
		
		for(String topic:topics) {
			topicModel.addElement(topic);
			//System.out.println(year);
		}

		topicList.setModel(topicModel);
		topicList.setSelectedIndex(0);
		topicList.setEditable(false);
		
		/////////////////
		
		search.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String yearPublished = (String)yearList.getSelectedItem();
				String topicSelected = (String)topicList.getSelectedItem();
				/*try {
					works = db.papersOfYear(yearPublished);
					for(String work:works) {
						System.out.println(work);
					}
				} catch (SQLException e1) {
					System.out.println("Failed to call papersOfYear in othersPanel");
				}*/
				new SelectedWorksFrame(yearPublished,topicSelected);
			}
			
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("Select criteria");
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
		add(new JLabel("Enter year:"), gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(yearList, gc);
		
		////////////Second row ///////////////////////////////////
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Enter topic:"), gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(topicList, gc);
		
		////////////Second row ///////////////////////////////////

		gc.gridy = 2;
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(search, gc);
		
	}


}
