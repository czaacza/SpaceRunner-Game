package model;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EndGameScene extends SpaceRunnerSubScene {

    private final static String BACKGROUND_IMAGE = "src/main/resources/view/menu/grey_button09.png";
    private final static int END_SUBSCENE_WIDTH = 550;
    private final static int END_SUBSCENE_HEIGHT = 400;
    private boolean isHidden = true;
    private int points;

    private SpaceRunnerButton quitButton;
    private NameSaver nameSaver;

    private Stage gameStage;
    private Stage menuStage;


    public EndGameScene(int points, Stage gameStage, Stage menuStage) {
        super(END_SUBSCENE_WIDTH, END_SUBSCENE_HEIGHT);
        this.points = points;
        this.gameStage = gameStage;
        this.menuStage = menuStage;

        setSubSceneBackground(BACKGROUND_IMAGE, END_SUBSCENE_WIDTH, END_SUBSCENE_HEIGHT);
        setLayoutX(600);
        setLayoutY(200);

        createLabels();
        createNameSaver();

        nameSaver.getSaveButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                saveResult();
            }
        });

        createQuitButton();

    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if (isHidden) {
            transition.setToX(-575);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }
        transition.play();
    }

    private void createQuitButton() {
        quitButton = new SpaceRunnerButton("Quit to menu", "-fx-background-color: transparent; -fx-background-image: url(blue_button13.png);",
                "-fx-background-color: transparent; -fx-background-image: url(grey_button04.png);");
        quitButton.setButtonFont(16);
        quitButton.setLayoutX(285);
        quitButton.setLayoutY(250);
        getPane().getChildren().add(quitButton);

        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gameStage.close();
                menuStage.show();
            }
        });

    }

    private void createLabels() {
        InfoLabel youLostLabel = new InfoLabel("You lost.");
        youLostLabel.setLabelFont(32);
        youLostLabel.setLayoutX(85);
        youLostLabel.setLayoutY(50);
        youLostLabel.setStyle("-fx-background-color: transparent;");

        String pointsString = "";
        if (points < 10) {
            pointsString += "00" + points;
        } else if (points >= 10 && points < 100) {
            pointsString += "0" + points;
        } else {
            pointsString += points;
        }
        InfoLabel pointsLabel = new InfoLabel("Points collected:  " + pointsString);
        pointsLabel.setLayoutX(85);
        pointsLabel.setLayoutY(110);
        pointsLabel.setStyle("-fx-background-color: transparent;");
        getPane().getChildren().add(youLostLabel);
        getPane().getChildren().add(pointsLabel);
    }

    private void createNameSaver() {
        nameSaver = new NameSaver();
        getPane().getChildren().add(nameSaver);
    }

    private void saveResult() {
        File outputFile = new File("src/main/resources/data/scores.txt");

        if (!outputFile.exists()) {
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Cannot create new file " + outputFile.getName());
            }
        }

        if (!outputFile.canWrite()) {
            System.out.println("No perrmisions to write in file " + outputFile.getName());
        }

        try {
            FileWriter fileWriter = new FileWriter(outputFile, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            if (!nameSaver.getNameField().getText().isEmpty() && nameSaver.getNameField().getText().length() < 30) {
                printWriter.println(nameSaver.getNameField().getText() + "," + points);
                gameStage.close();
                menuStage.show();
            } else {
                System.out.println("namefield is empty/too long");
            }

            printWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Cannot write to file " + outputFile.getName());
        }


    }

}
