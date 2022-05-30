package model.subscenes;

import model.InfoLabel;
import model.SpaceRunnerSubScene;

public class CreditsSubScene extends SpaceRunnerSubScene {
    private InfoLabel titleLabel;

    public CreditsSubScene(int width, int height) {
        super(width, height);
        createTitleLabel();
    }

    private void createTitleLabel() {
        titleLabel = new InfoLabel("Credits");
        titleLabel.setLayoutX(110);
        titleLabel.setLayoutY(50);

        getPane().getChildren().add(titleLabel);
    }
}
