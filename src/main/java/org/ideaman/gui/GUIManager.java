package org.ideaman.gui;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.ideaman.model.ChessManager;
import org.ideaman.model.piece.Piece;
import org.ideaman.model.piece.PieceType;
import org.ideaman.model.piece.Side;
import org.ideaman.utils.Position;

import java.util.List;

public class GUIManager
{
	ChessGUI chessGUI;
	ChessManager chessManager;

	public GUIManager(Stage stage)
	{
		chessGUI = new ChessGUI(stage, this);
		chessManager = new ChessManager(this);
		draw();
	}

	//this is the method that is called when one of the tile buttons is pressed
	protected void selectPiece(int x, int y)
	{
		chessManager.selectPiece(new Position(x, y));
	}

	//called by ChessManager
	public void setSelected(List<Position> selectedList)
	{
//		draw(); //TODO just clear the previous tile positions instead

		if (selectedList != null && selectedList.size() > 0)
		{
			for (Position position : selectedList)
			{
				TileGUI tile = chessGUI.getTileGUI(position);
				tile.setAsSelected();
			}
		}
	}

	//TODO instead of redrawing ever tile, redraw the tiles that were changed
	public void draw()
	{
		Piece[][] chessBoard = chessManager.getChessBoard();
		PieceImage instance = PieceImage.getInstance();
		for (int i = 0; i < chessBoard.length; i++)
		{
			for (int j = 0; j < chessBoard.length; j++)
			{
				Piece piece = chessBoard[i][j];
				Image img = instance.getImage(piece);

				TileGUI tileButton = chessGUI.getTileGUI(new Position(i, j));
				tileButton.resetSelect();
				tileButton.setPieceImg(img);
			}
		}
	}

	protected static class PieceImage
	{
		private static PieceImage instance = null;
		private Image[] images;

		private PieceImage()
		{
			setImages();
		}

		private void setImages()
		{
			images = new Image[12];
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			images[0] = new Image(classloader.getResourceAsStream("white_pawn.png"));
			images[1] = new Image(classloader.getResourceAsStream("black_pawn.png"));
			images[2] = new Image(classloader.getResourceAsStream("white_rook.png"));
			images[3] = new Image(classloader.getResourceAsStream("black_rook.png"));
			images[4] = new Image(classloader.getResourceAsStream("white_knight.png"));
			images[5] = new Image(classloader.getResourceAsStream("black_knight.png"));
			images[6] = new Image(classloader.getResourceAsStream("white_bishop.png"));
			images[7] = new Image(classloader.getResourceAsStream("black_bishop.png"));
			images[8] = new Image(classloader.getResourceAsStream("white_king.png"));
			images[9] = new Image(classloader.getResourceAsStream("black_king.png"));
			images[10] = new Image(classloader.getResourceAsStream("white_queen.png"));
			images[11] = new Image(classloader.getResourceAsStream("black_queen.png"));
		}

		public Image getImage(Piece piece)
		{
			if (piece == null)
				return null;

			Side side = piece.getSide();
			PieceType pieceType = piece.getType();
			int sideVal = side == Side.WHITE ? 0 : 1;
			int index;
			switch (pieceType) //TODO add an integer value to the PieceTypes or just use the enum order value
			{
				case PAWN:
					index = 0;
					break;
				case ROOK:
					index = 1;
					break;
				case KNIGHT:
					index = 2;
					break;
				case BISHOP:
					index = 3;
					break;
				case KING:
					index = 4;
					break;
				case QUEEN:
					index = 5;
					break;
				default:
					index = -1;
			}

			return images[2 * index + sideVal];
		}

		public static PieceImage getInstance()
		{
			if (instance == null)
				instance = new PieceImage();

			return instance;
		}
	}
}
