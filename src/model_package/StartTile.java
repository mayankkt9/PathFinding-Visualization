package model_package;

import java.awt.*;


public class StartTile extends PathTile {

	public static final char CHAR = 'S';

	public static final Color COLOR = new Color(255, 255, 51);

	public StartTile(Point point) {
		super(point);
	}

	public StartTile(int x, int y) {
		super(x, y);
	}

}
