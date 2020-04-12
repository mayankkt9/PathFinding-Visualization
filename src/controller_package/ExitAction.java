package controller_package;

import java.awt.event.ActionEvent;

public class ExitAction extends ActionItem {

	public ExitAction() {
		super("Exit");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
