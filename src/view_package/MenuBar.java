package view_package;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import controller_package.AStarPathAction;
import controller_package.BreadthFirstPathAction;
import controller_package.ClearAction;
import controller_package.DepthFirstPathAction;
import controller_package.ExitAction;
import controller_package.NewAction;
import controller_package.OpenAction;
import controller_package.SaveAction;
import model_package.PathMap;

public class MenuBar extends JMenuBar {

    public MenuBar(PathMap map, Window window){
        //File Menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new NewAction(map));
        fileMenu.add(new SaveAction(map));
        fileMenu.add(new OpenAction(map));
        fileMenu.add(new ExitAction());
        this.add(fileMenu);
        //Path Menu
        JMenu pathMenu = new JMenu("Path");
        pathMenu.add(new BreadthFirstPathAction(map));
        pathMenu.add(new DepthFirstPathAction(map));
        pathMenu.add(new AStarPathAction(map));
        pathMenu.add(new ClearAction(map));
        this.add(pathMenu);
    }

}

