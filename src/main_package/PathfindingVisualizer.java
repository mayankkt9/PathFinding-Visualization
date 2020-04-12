package main_package;

import model_package.PathMap;
import view_package.Window;

/**
 * @author Mayank Kataruka
 * Main class for running the program.
 */
public class PathfindingVisualizer {

    public static void main(String[] args){

        PathMap map = new PathMap(40, 40);
        Window window = new Window(map);

    }

}
