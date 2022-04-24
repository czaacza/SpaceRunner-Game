package view;

import model.InfoLabel;
import model.SpaceRunnerSubScene;

public class SubSceneManager {

    private final ViewManager viewManager;
    private final SpaceRunnerSubScene creditsSubScene;
    private final SpaceRunnerSubScene helpSubScene;
    private final SpaceRunnerSubScene scoresSubScene;
    private SpaceRunnerSubScene shipChooserSubScene;


    public SubSceneManager(ViewManager viewManager) {
        this.viewManager = viewManager;
        creditsSubScene = new SpaceRunnerSubScene();
        helpSubScene = new SpaceRunnerSubScene();
        scoresSubScene = new SpaceRunnerSubScene();
    }

    public void createSubScenes(){
        viewManager.getMainPane().getChildren().add(creditsSubScene);
        viewManager.getMainPane().getChildren().add(helpSubScene);
        viewManager.getMainPane().getChildren().add(scoresSubScene);
        createShipChooserSubScene();
    }

    private void createShipChooserSubScene() {
        shipChooserSubScene = new SpaceRunnerSubScene();
        viewManager.getMainPane().getChildren().add(shipChooserSubScene);

        InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(50);

        shipChooserSubScene.getPane().getChildren().add(chooseShipLabel);
        shipChooserSubScene.getPane().getChildren().add(viewManager.createShipsToChoose());
        shipChooserSubScene.getPane().getChildren().add(viewManager.createStartGameButton());
    }

    public SpaceRunnerSubScene getCreditsSubScene() {
        return creditsSubScene;
    }

    public SpaceRunnerSubScene getHelpSubScene() {
        return helpSubScene;
    }

    public SpaceRunnerSubScene getScoresSubScene() {
        return scoresSubScene;
    }

    public SpaceRunnerSubScene getShipChooserSubScene() {
        return shipChooserSubScene;
    }
}
