package org.ideaman.gui;

import org.ideaman.model.ChessManager;

public class GUIManager
{
	ChessGUI chessGUI;
	ChessManager chessManager;

	public GUIManager(String[] args)
	{
		chessGUI = new ChessGUI(args);
		chessManager = new ChessManager();
	}
}
