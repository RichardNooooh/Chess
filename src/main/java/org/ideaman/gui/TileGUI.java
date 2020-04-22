package org.ideaman.gui;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class TileGUI
{

	StackPane layeredImages;
	Button tileButton;

	protected TileGUI(int x, int y, GUIManager guiManager)
	{
		tileButton = new Button();

		tileButton.setMinSize(75,75);
		tileButton.setMaxSize(75,75);

		tileButton.setOnAction(e -> guiManager.selectPiece(x, y));

		layeredImages = new StackPane();
		tileButton.setGraphic(layeredImages);
	}

	protected Button getButton()
	{
		return tileButton;
	}

	//TODO adds the top StackPane image as the circle
	protected void setAsSelected()
	{
		Group selectCircle = new Group(new Circle(10, 10, 10));
		selectCircle.setOpacity(0.3);

		//TODO check and make sure this isn't already selected
		layeredImages.getChildren().add(selectCircle);
	}

	// removes the top StackPane image, but not the piece image if there is one
	protected void resetSelect()
	{
		ObservableList<Node> imageList = layeredImages.getChildren();
		if (imageList.size() == 1) //blank tile with only the selection circle
			imageList.remove(0, 1);
		else if (imageList.size() == 2) //tile with a piece and selection circle
			imageList.remove(1, 2);
	}

	public void setPieceImg(Image img)
	{
		ObservableList<Node> imageList = layeredImages.getChildren();
		imageList.clear();
		imageList.add(new ImageView(img));
	}

	//TODO adds the piece image to the stackpane; make sure there is no circle selection
	//protected void setChessPieceImage()
}
