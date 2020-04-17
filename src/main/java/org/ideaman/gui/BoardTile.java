package org.ideaman.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import org.ideaman.manager.ChessManager;
import org.ideaman.manager.Position;
import org.ideaman.piece.Piece;
import org.ideaman.piece.PieceType;
import org.ideaman.piece.Side;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BoardTile
{
	private Side side;
	private SelectionStatus selectStatus;

	private Button button;
	private Piece piece;
	ChessManager manager;

	public BoardTile(boolean isWhite, Piece piece, ChessManager manager)
	{
		this.manager = manager;
		this.piece = piece;
		this.side = isWhite ? Side.WHITE : Side.BLACK;

		selectStatus = SelectionStatus.NOT_SELECTED;


		button = new Button();
		button.setMinSize(75,75);
		String color = "-fx-background-color: " + (side == Side.WHITE ? "#ffffff" : "#000000") + ";";
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

	static class PieceImage
	{
		private static PieceImage instance = null;
		private Image[] images;

		private PieceImage()
		{
			try
			{
				setImages();
			} catch(FileNotFoundException e)
			{
				System.out.println("File not found for images");
			}
		}

		private void setImages() throws FileNotFoundException
		{
			String path = "resources/images/";
			images = new Image[12];

			images[0] = new Image(new FileInputStream(path + "white_pawn"));
			images[1] = new Image(new FileInputStream(path + "black_pawn"));
			images[2] = new Image(new FileInputStream(path + "white_rook"));
			images[3] = new Image(new FileInputStream(path + "black_rook"));
			images[4] = new Image(new FileInputStream(path + "white_knight"));
			images[5] = new Image(new FileInputStream(path + "black_knight"));
			images[6] = new Image(new FileInputStream(path + "white_bishop"));
			images[7] = new Image(new FileInputStream(path + "black_bishop"));
			images[8] = new Image(new FileInputStream(path + "white_king"));
			images[9] = new Image(new FileInputStream(path + "black_king"));
			images[10] = new Image(new FileInputStream(path + "white_queen"));
			images[11] = new Image(new FileInputStream(path + "black_queen"));
		}

		public Image getImage(Side side, PieceType pieceType)
		{
			int multiple = side == Side.WHITE ? 0 : 1;
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

			return images[multiple * index];
		}

		public static PieceImage getInstance()
		{
			if (instance == null)
				instance = new PieceImage();

			return instance;
		}
	}
}
