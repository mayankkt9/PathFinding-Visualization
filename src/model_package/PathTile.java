package model_package;

import java.awt.*;

public class PathTile {

    public static final char CHAR = 'P';

    public static final Color COLOR = Color.white;
    public static final Color COLOR_FRONTIER = new Color(246, 255, 102);
    public static final Color COLOR_TRAVERSED = new Color(193, 228, 255);

    //Has this tile already been traversed?
    private boolean traversed = false;
    //Is this tile next in line to be evaluated?
    private boolean inFrontier = false;

    private Point point;

    public PathTile(Point point){
        this.point = point;
    }

    public PathTile(int x, int y){
        this.point = new Point(x, y);
    }

    public boolean isTraversed(){
        return this.traversed;
    }

    public boolean isInFrontier(){
        return this.inFrontier;
    }

    /**
     * Sets this tile as traversed.
     * @param value whether or not the tile is traversed.
     */
    public void setTraversed(boolean value){
        this.traversed = value;
    }

    /**
     * Sets this tile as in the frontier.
     * @param value whether or not the tile is in the frontier.
     */
    public void setInFrontier(boolean value){
        this.inFrontier = value;
    }

    public int getX(){
        return this.point.x;
    }

    public int getY(){
        return this.point.y;
    }

    public Point getPoint(){
        return this.point;
    }

}
