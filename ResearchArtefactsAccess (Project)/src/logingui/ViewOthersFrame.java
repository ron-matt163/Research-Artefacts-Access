package logingui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ViewOthersFrame extends JFrame {
	
	private ViewOthersPanel othersPanel;
	
	public ViewOthersFrame() {
		
		super("View Works of Other Researchers");
		setLayout(new BorderLayout());
		
		othersPanel = new ViewOthersPanel();
		
		add(othersPanel,BorderLayout.CENTER);
		
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
