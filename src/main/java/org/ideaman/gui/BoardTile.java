package org.ideaman.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class BoardTile
{
	private boolean isWhiteColor;
	private Button button;
	private boolean isSelected;
	private int ID;
	private static int total;

	public BoardTile(boolean isWhite)
	{
		isWhiteColor = isWhite;
		button = new Button();

		button.setOnAction(new SelectionAction());
//		button.setMaxSize(100,100);
		button.setMinSize(75,75);
		String color = "-fx-background-color: " + (isWhite ? "#ffffff" : "#000000") + ";";
		button.setStyle(color + "-fx-background-radius: 0px;");


		ID = total++;
	}

	public Button getButton()
	{
		return button;
	}

	private class SelectionAction<ActionEvent> implements EventHandler
	{
		@Override
		public void handle(Event event)
		{
			System.out.println(ID);
		}
	}


}
