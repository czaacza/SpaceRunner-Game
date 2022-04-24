package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.SpaceRunnerButton;
import model.SpaceRunnerSubScene;

import java.util.ArrayList;
import java.util.List;

public class ButtonManager {

    private final List<SpaceRunnerButton> menuButtons;
    private final ViewManager viewManager;
    private final SubSceneManager subSceneManager;
    private SpaceRunnerSubScene sceneToHide;


    public ButtonManager(ViewManager viewManager, SubSceneManager subSceneManager) {
        this.menuButtons = new ArrayList<SpaceRunnerButton>();
        this.viewManager =viewManager;
        this.subSceneManager = subSceneManager;
    }

    public void createButtons(){
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
        viewManager.getMainPane().getChildren().addAll(menuButtons);
    }

    private void addMenuButton(SpaceRunnerButton button){
        button.setLayoutX(100);
        button.setLayoutY(150 + menuButtons.size() * 100);
        menuButtons.add(button);
    }

    private void showSubScene(SpaceRunnerSubScene subScene){
        if(sceneToHide != null){
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createStartButton(){
        SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
        addMenuButton(startButton);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubScene(subSceneManager.getShipChooserSubScene());
            }
        });
    }

    private void createScoresButton(){
        SpaceRunnerButton scoresButton = new SpaceRunnerButton("SCORES");
        addMenuButton(scoresButton);
        scoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubScene(subSceneManager.getScoresSubScene());
            }
        });
    }

    private void createHelpButton(){
        SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
        addMenuButton(helpButton);
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubScene(subSceneManager.getHelpSubScene());
            }
        });
    }

    private void createCreditsButton(){
        SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
        addMenuButton(creditsButton);
        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubScene(subSceneManager.getCreditsSubScene());
            }
        });
    }

    private void createExitButton(){
        SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                viewManager.getMainStage().close();
            }
        });

    }

    public List<SpaceRunnerButton> getMenuButtons() {
        return menuButtons;
    }
}
