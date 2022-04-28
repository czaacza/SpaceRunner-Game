package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import view.GameViewManager;

import java.util.ArrayList;
import java.util.List;

public class ShipChooserSubScene extends SpaceRunnerSubScene{

    private List<ShipPicker> shipsList;
    private SHIP chosenShip;
    private Stage menuStage;

    public ShipChooserSubScene(Stage menuStage) {
        this.menuStage = menuStage;
        InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(50);

        getPane().getChildren().add(chooseShipLabel);
        getPane().getChildren().add(createShipsToChoose());
        getPane().getChildren().add(createStartGameButton());
    }

    private HBox createShipsToChoose(){
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        shipsList = new ArrayList<ShipPicker>();
        for(SHIP ship : SHIP.values()){
            ShipPicker shipToPick = new ShipPicker(ship);
            shipsList.add(shipToPick);
            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for(ShipPicker ship : shipsList){
                        ship.setCircleChosen(false);
                    }
                    shipToPick.setCircleChosen(true);
                    chosenShip = shipToPick.getShip();
                }
            });
        }
        hBox.getChildren().addAll(shipsList);
        hBox.setLayoutX(300 - (110 * 2));
        hBox.setLayoutY(120);

        return hBox;
    }

    private SpaceRunnerButton createStartGameButton(){
        String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url(grey_button01.png);";
        String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url(grey_button00.png);";

        SpaceRunnerButton startGameButton = new SpaceRunnerButton("START", BUTTON_FREE_STYLE, BUTTON_PRESSED_STYLE);
        startGameButton.setLayoutX(200);
        startGameButton.setLayoutY(295);
        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(chosenShip != null){
                    GameViewManager gameViewManager = new GameViewManager();
                    gameViewManager.createNewGame(menuStage, chosenShip);
                }
            }
        });

        return startGameButton;
    }

}
