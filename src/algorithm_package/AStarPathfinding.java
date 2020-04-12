package algorithm_package;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import model_package.GoalTile;
import model_package.PathMap;
import model_package.PathTile;

public class AStarPathfinding extends Pathfinding {

	private Map<PathTile, PathTile> cameFrom = new HashMap<>();

	private Map<PathTile, Integer> fScore = new HashMap<>();
	private Map<PathTile, Integer> gScore = new HashMap<>();

	private PriorityQueue<PathTile> frontierQueue = new PriorityQueue<>((o1, o2) -> {
		if (fScore.get(o1) < fScore.get(o2)) {
			return -1;
		} else if (fScore.get(o1).intValue() == (fScore.get(o2)).intValue()) {
			return 0;
		} else {
			return 1;
		}
	});

	private GoalTile goalTile;

	public AStarPathfinding(PathMap map, int solutionStrategy) {
		super(map, solutionStrategy);
	}

	@Override
	protected void step() {
		if (this.frontierQueue.isEmpty()) {
			this.finished = true;
			return;
		}
		PathTile currentTile = this.frontierQueue.remove();
		if (currentTile.equals(goalTile)) {
			System.out.println("Goal found");
			this.finished = true;
			return;
		}
		currentTile.setTraversed(true);
		currentTile.setInFrontier(false);
		for (PathTile tile : this.map.getAdjacentTiles(currentTile)) {
			int tentativeGScore = this.gScore.get(currentTile) + 1;
			if (tentativeGScore >= this.gScore.get(tile)) {
				continue;
			}
			if (!this.frontierQueue.contains(tile)) {
				this.frontierQueue.add(tile);
				tile.setInFrontier(true);
			}
			this.cameFrom.put(tile, currentTile);
			this.gScore.put(tile, tentativeGScore);
			this.fScore.put(tile, tentativeGScore + hValue(tile));
		}

	}

	@Override
	protected void init() {
		this.goalTile = this.goalPoints[0];
		this.frontierQueue.add(this.startPoints[0]);
		for (int x = 0; x < this.map.getWidth(); x++) {
			for (int y = 0; y < this.map.getHeight(); y++) {
				this.fScore.put(this.map.getTile(x, y), Integer.MAX_VALUE);
				this.gScore.put(this.map.getTile(x, y), Integer.MAX_VALUE);
			}
		}
		this.fScore.put(this.startPoints[0], hValue(this.startPoints[0]));
		this.gScore.put(this.startPoints[0], 0);
	}

	/**
	 * Gets the heuristic value of a tile.
	 * 
	 * @param tile The tile to get the heuristic value of.
	 * @return The manhattan distance between the goal and this tile.
	 */
	private int hValue(PathTile tile) {
		return Math.abs(goalTile.getX() - tile.getX()) + Math.abs(goalTile.getY() - tile.getY());
		// return (int) Math.sqrt(Math.pow(goalTile.getX() - tile.getX(), 2) +
		// Math.pow(goalTile.getY() - tile.getY(), 2));
	}

}
