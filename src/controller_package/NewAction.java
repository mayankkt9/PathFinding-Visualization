package controller_package;

import java.awt.event.ActionEvent;

import model_package.PathMap;

public class NewAction extends ActionItem {

    private PathMap map;

    public NewAction(PathMap map){
        super("New");
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.map.clear();
        this.map.manualNotify();
    }
}
