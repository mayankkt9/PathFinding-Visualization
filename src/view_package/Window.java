package view_package;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import model_package.PathMap;

public class Window extends JFrame {

	private MapPanel mapPanel;

	public Window(PathMap pathMap) {
		this.mapPanel = new MapPanel(pathMap);
		initFrame(pathMap);
	}

	private void initFrame(PathMap map) {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setContentPane(this.mapPanel);
		this.setJMenuBar(new MenuBar(map, this));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setEnabled(true);
	}

	public PathMap getMap() {
		return this.mapPanel.getMap();
	}

}
