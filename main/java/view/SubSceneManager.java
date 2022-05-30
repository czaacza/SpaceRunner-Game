package view;

import model.subscenes.CreditsSubScene;
import model.subscenes.DefaultSubScene;
import model.subscenes.ScoresSubScene;
import model.subscenes.ShipChooserSubScene;
import model.SpaceRunnerSubScene;

public class SubSceneManager {

    private final ViewManager viewManager;
    private final DefaultSubScene defaultSubScene;
    private final CreditsSubScene creditsSubScene;
    private final SpaceRunnerSubScene helpSubScene;
    private final ScoresSubScene scoresSubScene;
    private final ShipChooserSubScene shipChooserSubScene;

    private static final int SUBSCENE_WIDTH = 600;
    private static final int SUBSCENE_HEIGHT = 400;

    public SubSceneManager(ViewManager viewManager) {
        this.viewManager = viewManager;
        this.defaultSubScene = new DefaultSubScene();
        creditsSubScene = new CreditsSubScene(SUBSCENE_WIDTH, SUBSCENE_HEIGHT);
        helpSubScene = new SpaceRunnerSubScene(SUBSCENE_WIDTH, SUBSCENE_HEIGHT);
        scoresSubScene = new ScoresSubScene(SUBSCENE_WIDTH, SUBSCENE_HEIGHT);
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

    public ScoresSubScene getScoresSubScene() {
        return scoresSubScene;
    }

    public SpaceRunnerSubScene getShipChooserSubScene() {
        return shipChooserSubScene;
    }

    public DefaultSubScene getDefaultSubScene() {
        return defaultSubScene;
    }
}
