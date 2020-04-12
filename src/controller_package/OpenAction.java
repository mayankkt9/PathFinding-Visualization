package controller_package;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;

import model_package.PathMap;

public class OpenAction extends ActionItem {

    private PathMap map;

    public OpenAction(PathMap map){
        super("Open");
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog((Component) e.getSource());
        if (result == JFileChooser.APPROVE_OPTION){
            this.map.load(fc.getSelectedFile().getPath());
        }
    }
}
