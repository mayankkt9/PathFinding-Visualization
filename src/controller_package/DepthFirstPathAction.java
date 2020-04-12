package controller_package;

import java.awt.event.ActionEvent;

import algorithm_package.DepthFirstPathfinding;
import algorithm_package.Pathfinding;
import model_package.PathMap;

public class DepthFirstPathAction extends ActionItem {

    private DepthFirstPathfinding dfs;

    public DepthFirstPathAction(PathMap map){
        super("Depth First");
        this.dfs = new DepthFirstPathfinding(map, Pathfinding.FIRST_SOLUTION);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(this.dfs).start();
    }
}
