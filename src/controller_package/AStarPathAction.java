package controller_package;

import java.awt.event.ActionEvent;

import algorithm_package.AStarPathfinding;
import algorithm_package.Pathfinding;
import model_package.PathMap;

public class AStarPathAction extends ActionItem {

    private AStarPathfinding aStar;

    public AStarPathAction(PathMap map){
        super("A-Star");
        this.aStar = new AStarPathfinding(map, Pathfinding.FIRST_SOLUTION);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(this.aStar).start();
    }
}
