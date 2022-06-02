package model.subscenes;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import model.InfoLabel;
import model.SpaceRunnerSubScene;

public class CreditsSubScene extends SpaceRunnerSubScene {
    private InfoLabel titleLabel;
    private InfoLabel [] descriptionLabels;

    public CreditsSubScene(int width, int height) {
        super(width, height);
        createTitleLabel();
        createDescriptionLabel();
    }

    private void createTitleLabel() {
        titleLabel = new InfoLabel("Credits");
        titleLabel.setLayoutX(110);
        titleLabel.setLayoutY(50);

        getPane().getChildren().add(titleLabel);
    }

    private void createDescriptionLabel(){
        descriptionLabels = new InfoLabel[4];

        descriptionLabels[0] = new InfoLabel("Author:", false);
        descriptionLabels[0].setLayoutX(110);
        descriptionLabels[0].setLayoutY(120);
        descriptionLabels[0].setAlignment(Pos.CENTER);

        descriptionLabels[1] = new InfoLabel("czaacza", false);
        descriptionLabels[1].setTextFill(Paint.valueOf("#fff"));
        descriptionLabels[1].setLayoutX(110);
        descriptionLabels[1].setLayoutY(170);
        descriptionLabels[1].setAlignment(Pos.CENTER);

        descriptionLabels[2] = new InfoLabel("Inspiration:", false);
        descriptionLabels[2].setAlignment(Pos.CENTER);
        descriptionLabels[2].setLayoutX(110);
        descriptionLabels[2].setLayoutY(240);

        descriptionLabels[3] = new InfoLabel("youtube.com/javacraving", false);
        descriptionLabels[3].setTextFill(Paint.valueOf("#fff"));
        descriptionLabels[3].setPrefWidth(500);
        descriptionLabels[3].setAlignment(Pos.CENTER);
        descriptionLabels[3].setLayoutX(60);
        descriptionLabels[3].setLayoutY(290);

        getPane().getChildren().addAll(descriptionLabels);
    }

    private void createOnClick(){
        descriptionLabels[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                
            }
        });

    }
}
