package model_package;

import java.awt.*;

public class GoalTile extends PathTile {

	public static final char CHAR = 'G';

	public static final Color COLOR = new Color(51, 51, 255);

	public GoalTile(Point point) {
		super(point);
	}

	public GoalTile(int x, int y) {
		super(x, y);
	}

}
