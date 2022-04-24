package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ShipPicker extends VBox {

    private ImageView circleImage;
    private ImageView shipImage;

    private final String circleNotChosenURL = "file:src/main/resources/view/shipchooser/grey_circle.png";
    private final String circleChosenURL = "file:src/main/resources/view/shipchooser/blue_boxTick.png";

    private SHIP ship;
    private boolean isCircleChosen;

    public ShipPicker(SHIP ship) {
        circleImage = new ImageView(new Image(circleNotChosenURL));
        shipImage = new ImageView(new Image(ship.getShipURL(), 99, 75, true, false));
        this.ship = ship;
        isCircleChosen = false;

        setAlignment(Pos.CENTER);
        setSpacing(20);
        getChildren().add(circleImage);
        getChildren().add(shipImage);
    }

    public SHIP getShip() {
        return ship;
    }

    public boolean isCircleChosen() {
        return isCircleChosen;
    }

    public void setCircleChosen(boolean circleChosen) {
        isCircleChosen = circleChosen;
        String imageURLToSet = this.isCircleChosen ? circleChosenURL : circleNotChosenURL;
        circleImage.setImage(new Image(imageURLToSet));
    }
}
