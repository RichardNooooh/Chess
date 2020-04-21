package org.ideaman;

import javafx.application.Application;
import javafx.stage.Stage;
import org.ideaman.gui.GUIManager;

/**
 * A wrapper starting class.
 *
 */
public class ChessLauncher extends Application
{
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		new GUIManager(stage);
	}
}
