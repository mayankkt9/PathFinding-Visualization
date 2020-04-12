package controller_package;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public abstract class ActionItem extends JMenuItem implements ActionListener {

    public ActionItem(String displayString){
        super(displayString);
        this.addActionListener(this);
    }

}
