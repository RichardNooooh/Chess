package org.ideaman.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.ideaman.manager.ChessManager;
import org.ideaman.manager.Position;
import org.ideaman.piece.Piece;
import org.ideaman.piece.PieceType;
import org.ideaman.piece.Side;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

public class BoardTile
{
	private SelectionStatus selectStatus;

	private Button button;
	private Piece piece;
	private boolean isWhite;
	ChessManager manager;

	public BoardTile(boolean isWhite, Piece piece, ChessManager manager)
	{
		this.manager = manager;
		this.piece = piece;
		this.isWhite = isWhite;

		selectStatus = SelectionStatus.NOT_SELECTED;
		button = new Button();
		if (piece != null)
		{
			PieceImage imageLibrary = PieceImage.getInstance();
			Image chessImage = imageLibrary.getImage(piece.getSide(), piece.getType());
			button.setGraphic(new ImageView(chessImage));
		}
		button.setMinSize(75,75);
		button.setMaxSize(75,75);
		String color = "-fx-background-color: " + (isWhite ? "#ffce9e" : "#d18b47") + ";";
		button.setStyle(color + "-fx-background-radius: 0px;");
		button.setOnAction(e -> {
			if (piece != null)
				manager.select(piece.getPosition());});
	}

	public Button getButton()
	{
		return button;
	}

	public void setSelection(SelectionStatus status)
	{
		selectStatus = status;
		updateImage();
	}

	private void updateImage()
	{
		//TODO set the boardTile background color/image
	}

	public String toString()
	{
		return piece.toString();
	}

	static class PieceImage
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

		public Image getImage(Side side, PieceType pieceType)
		{
			int sideVal = side == Side.WHITE ? 0 : 1;
			int index;
			switch (pieceType)
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
