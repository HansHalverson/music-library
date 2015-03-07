import java.awt.*;
import java.io.File;
import java.util.HashMap;

import javax.swing.*;

//In Progress... current code not meant to be used

public class Window {
	public Window() {
		JFrame frame = new JFrame("Music Library");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainPanel mainPanel = new MainPanel();
		JScrollPane scrollPanel = new JScrollPane(mainPanel);
		scrollPanel.add(new JScrollBar(JScrollBar.VERTICAL));
		frame.setContentPane(scrollPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public class MainPanel extends JPanel {
		public MainPanel() {
			Dimension panelSize = new Dimension(600, 400);
			File input = new File("/Users/HansHalverson/Music/iTunes/iTunes Music Library.xml");
			HashMap<Integer, MusicFile> library = XMLparser.parseXML(input);
			for (MusicFile song : library.values()) {
				JLabel title = new JLabel(song.getName());
				this.add(title);
			}
			this.setPreferredSize(panelSize);
		}
	}
	
	public static void main(String[] args) {
		Window window = new Window();
	}
}
