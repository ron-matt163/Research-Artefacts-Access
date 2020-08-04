package logingui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class SelectedWorksFrame extends JFrame {
	
	private SelectedWorksPanel selectedPanel;
	private TextPanel textPanel;

	public SelectedWorksFrame(String year,String topic) {
		
		super("Selected Files");
		setLayout(new BorderLayout());
		
		selectedPanel = new SelectedWorksPanel(year,topic);
		textPanel = new TextPanel();
		
		selectedPanel.setViewEvent(new ViewFileListener() {
			public void viewFileOccured(String text,String fileName) {
				textPanel.set(text);
			}
			
		});
		
		add(selectedPanel,BorderLayout.WEST);
		add(textPanel,BorderLayout.EAST);
		
		setSize(1200, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}
