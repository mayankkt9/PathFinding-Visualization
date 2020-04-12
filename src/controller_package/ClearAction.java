package controller_package;

import java.awt.event.ActionEvent;

import model_package.PathMap;

public class ClearAction extends ActionItem {

    private PathMap map;

    public ClearAction(PathMap map) {
        super("Clear");
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.map.clearPaths();
    }
}
