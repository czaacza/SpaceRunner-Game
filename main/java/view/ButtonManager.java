package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.SpaceRunnerButton;

import java.util.ArrayList;
import java.util.List;

public class ButtonManager {

    private final List<SpaceRunnerButton> menuButtons;
    private ViewManager viewManager;

    public ButtonManager(ViewManager viewManager) {
        this.menuButtons = new ArrayList<SpaceRunnerButton>();
        this.viewManager =viewManager;
    }

    public void createMenuButtons(){
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    private void addMenuButton(SpaceRunnerButton button){
        button.setLayoutX(100);
        button.setLayoutY(150 + menuButtons.size() * 100);
        menuButtons.add(button);
    }

    private void createStartButton(){
        SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
        addMenuButton(startButton);
    }

    private void createScoresButton(){
        SpaceRunnerButton scoresButton = new SpaceRunnerButton("SCORES");
        addMenuButton(scoresButton);
    }

    private void createHelpButton(){
        SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
        addMenuButton(helpButton);
    }

    private void createCreditsButton(){
        SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
        addMenuButton(creditsButton);
        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                viewManager.getCreditsSubScene().moveSubScene();
            }
        });
    }

    private void createExitButton(){
        SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
        addMenuButton(exitButton);
    }

    public List<SpaceRunnerButton> getMenuButtons() {
        return menuButtons;
    }
}
