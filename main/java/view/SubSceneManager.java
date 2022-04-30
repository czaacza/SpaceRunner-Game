package view;

import model.DefaultSubScene;
import model.ShipChooserSubScene;
import model.SpaceRunnerSubScene;

public class SubSceneManager {

    private final ViewManager viewManager;
    private final DefaultSubScene defaultSubScene;
    private final SpaceRunnerSubScene creditsSubScene;
    private final SpaceRunnerSubScene helpSubScene;
    private final SpaceRunnerSubScene scoresSubScene;
    private final ShipChooserSubScene shipChooserSubScene;


    public SubSceneManager(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.defaultSubScene = new DefaultSubScene();
        creditsSubScene = new SpaceRunnerSubScene();
        helpSubScene = new SpaceRunnerSubScene();
        scoresSubScene = new SpaceRunnerSubScene();
        shipChooserSubScene = new ShipChooserSubScene(viewManager.getMainStage());
    }

    public void createSubScenes(){
        viewManager.getMainPane().getChildren().add(defaultSubScene);
        viewManager.getMainPane().getChildren().add(creditsSubScene);
        viewManager.getMainPane().getChildren().add(helpSubScene);
        viewManager.getMainPane().getChildren().add(scoresSubScene);
        viewManager.getMainPane().getChildren().add(shipChooserSubScene);
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

    public DefaultSubScene getDefaultSubScene() {
        return defaultSubScene;
    }
}
