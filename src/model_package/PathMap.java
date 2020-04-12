package model_package;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class PathMap extends Observable {

    //2-D map of all the tiles of the map.
    private PathTile[][] tileMap;

    public PathMap(int width, int height){
        tileMap = new PathTile[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                tileMap[x][y] = new PathTile(new Point(x,y));
            }
        }
    }

    public PathMap(String filename){
        this.load(filename);
    }

    /**
     * Gets the path tile at a specific coordinate.
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return The tile that exists at these coordinates, or an exception if the coordinates are out of bounds.
     */
    public PathTile getTile(int x, int y){
        return this.tileMap[x][y];
    }

    /**
     * Gets the path tile at a specific coordinate.
     * @param p The Point of the tile.
     * @return The tile that exists at this coordinates.
     */
    public PathTile getTile(Point p){
        return this.tileMap[p.x][p.y];
    }

    /**
     * Returns the width of the map: the number of tiles on the x-axis.
     * @return Integer value depicting number of tiles on the x-axis.
     */
    public int getWidth(){
        return this.tileMap.length;
    }

    /**
     * Returns the height of the map.
     * @return Integer value depicting the number of tiles on the y-axis.
     */
    public int getHeight(){
        return this.tileMap[0].length;
    }

    /**
     * Sets a tile to a new tile. The location is given in the Tile object.
     * This will update each time, so it is not intended for rapid drawing.
     * @param tile The tile to set.
     */
    public void setTile(PathTile tile){
        if (isValidPoint(tile.getPoint())){
            this.tileMap[tile.getX()][tile.getY()] = tile;
            this.manualNotify();
        }
    }

    /**
     * Sets a tile as traversed.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param value Set as either true or false.
     */
    public void setTraversed(int x, int y, boolean value){
        tileMap[x][y].setTraversed(value);
        this.manualNotify();
    }

    public void manualNotify(){
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Gets a list of starting points in a map. These are locations that a pathfinding algorithm should start from.
     * @return An array of Points.
     */
    public StartTile[] getStartPoints(){
        List<StartTile> points = new ArrayList<>();
        for (int x = 0; x < this.getWidth(); x++){
            for (int y = 0; y < this.getHeight(); y++){
                if (this.tileMap[x][y] instanceof StartTile){
                    points.add((StartTile)this.tileMap[x][y]);
                }
            }
        }
        StartTile[] pointsArray = new StartTile[points.size()];
        points.toArray(pointsArray);
        return pointsArray;
    }

    /**
     * Gets a list of goal points in a map. These are locations that a pathfinding algorithm should go to.
     * @return An array of Points.
     */
    public GoalTile[] getGoalPoints(){
        List<GoalTile> points = new ArrayList<>();
        for (int x = 0; x < this.getWidth(); x++){
            for (int y = 0; y < this.getHeight(); y++){
                if (this.tileMap[x][y] instanceof GoalTile){
                    points.add((GoalTile)this.tileMap[x][y]);
                }
            }
        }
        GoalTile[] pointsArray = new GoalTile[points.size()];
        points.toArray(pointsArray);
        return pointsArray;
    }

    /**
     * Determines if a point is valid for this map, so that there are no out of bounds errors.
     * @param p The point.
     * @return True if it is safe to index this point.
     */
    boolean isValidPoint(Point p){
        return (p.x >= 0 && p.x < this.getWidth() && p.y >= 0 && p.y < this.getHeight());
    }

    /**
     * Gets a list of tiles adjacent to this tile. That is, up, down, left, and right of this tile.
     * @param originTile The tile to get adjacent tiles to.
     * @return A list of path tiles adjacent to a given tile.
     */
    public List<PathTile> getAdjacentTiles(PathTile originTile){
        List<PathTile> tiles = new ArrayList<>();
        Point originPoint = originTile.getPoint();
        Point up = new Point(originPoint);
        up.translate(0, -1);
        if (isValidPoint(up) && !(this.getTile(up) instanceof ObstacleTile))
            tiles.add(this.getTile(up));
        Point right = new Point(originPoint);
        right.translate(1, 0);
        if (isValidPoint(right) && !(this.getTile(right) instanceof ObstacleTile))
            tiles.add(this.getTile(right));
        Point down = new Point(originPoint);
        down.translate(0, 1);
        if (isValidPoint(down) && !(this.getTile(down) instanceof ObstacleTile))
            tiles.add(this.getTile(down));
        Point left = new Point(originPoint);
        left.translate(-1, 0);
        if (isValidPoint(left) && !(this.getTile(left) instanceof ObstacleTile))
            tiles.add(this.getTile(left));
        return tiles;
    }

    /**
     * Clears traversed, frontier, and path markers.
     */
    public void clearPaths(){
        for (int x = 0; x < this.getWidth(); x++){
            for (int y = 0; y < this.getHeight(); y++){
                this.tileMap[x][y].setTraversed(false);
                this.tileMap[x][y].setInFrontier(false);
            }
        }
        this.manualNotify();
    }

    /**
     * Saves the map to the specified file.
     * @param filename The name of the file to save to.
     */
    public void save(String filename){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"))){
            StringBuilder sb = new StringBuilder();
            sb.append(this.getWidth()).append('\n').append(this.getHeight()).append('\n');
            for (int x = 0; x < this.getWidth(); x++){
                for (int y = 0; y < this.getHeight(); y++){
                    if (this.getTile(x, y) instanceof StartTile) {
                        sb.append(StartTile.CHAR);
                    } else if (this.getTile(x, y) instanceof GoalTile){
                        sb.append(GoalTile.CHAR);
                    } else if (this.getTile(x, y) instanceof ObstacleTile){
                        sb.append(ObstacleTile.CHAR);
                    } else {
                        sb.append(PathTile.CHAR);
                    }
                }
                sb.append('\n');
            }
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a map from a file.
     * @param filename The name of the file to load from.
     */
    public void load(String filename){
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            int width = Integer.parseInt(lines.remove(0));
            int height = Integer.parseInt(lines.remove(0));
            this.tileMap = new PathTile[width][height];
            for (int x = 0; x < width; x++){
                for (int y = 0; y < height; y++){
                    switch (lines.get(y).charAt(x)){
                        case StartTile.CHAR:
                            this.tileMap[x][y] = new StartTile(x, y);
                            break;
                        case GoalTile.CHAR:
                            this.tileMap[x][y] = new GoalTile(x, y);
                            break;
                        case ObstacleTile.CHAR:
                            this.tileMap[x][y] = new ObstacleTile(x, y);
                            break;
                        default:
                            this.tileMap[x][y] = new PathTile(x, y);
                    }
                }
            }
            this.manualNotify();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the current map of any tiles other than default path tiles.
     */
    public void clear(){
        for (int x = 0; x < this.getWidth(); x++){
            for (int y = 0; y < this.getHeight(); y++){
                this.tileMap[x][y] = new PathTile(x, y);
            }
        }
    }

}
