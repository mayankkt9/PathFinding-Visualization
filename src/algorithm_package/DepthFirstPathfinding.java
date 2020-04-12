package algorithm_package;

import java.util.Arrays;
import java.util.Stack;

import model_package.GoalTile;
import model_package.PathMap;
import model_package.PathTile;

public class DepthFirstPathfinding extends Pathfinding {

    //Stack, used specifically for depth first.
    private Stack<PathTile> frontierStack = new Stack<>();

    public DepthFirstPathfinding(PathMap map, int solutionStrategy){
        super(map, solutionStrategy);
    }

    @Override
    protected void init() {
        this.frontierStack.addAll(Arrays.asList(this.startPoints));
    }

    @Override
    protected void step() {
        if (this.frontierStack.isEmpty()){
            this.finished = true;
            return;
        }
        PathTile currentTile = this.frontierStack.pop();
        currentTile.setInFrontier(false);
        if (currentTile instanceof GoalTile){
            this.finished = true;
            return;
        }
        if (!currentTile.isTraversed()){
            currentTile.setTraversed(true);
            for (PathTile tile : this.map.getAdjacentTiles(currentTile)){
                if (!tile.isTraversed()) {
                    this.frontierStack.push(tile);
                    tile.setInFrontier(true);
                }
            }
        }
    }

}
