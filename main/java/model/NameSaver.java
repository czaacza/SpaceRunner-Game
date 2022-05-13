package model;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class NameSaver extends VBox {

    private final String FONT_PATH = "src/main/resources/model/kenvector_future.ttf";
    private final int FONT_SIZE = 16;
    private final TextField nameField;
    private final SpaceRunnerButton saveButton;


    public NameSaver() {
        this.nameField = new TextField();
        nameField.setPromptText("Your name");
        nameField.setAlignment(Pos.CENTER_LEFT);
        nameField.setMaxWidth(180);
        nameField.setPrefWidth(160);
        nameField.setPrefHeight(40);

        try {
            nameField.setFont(Font.loadFont(new FileInputStream(FONT_PATH), FONT_SIZE));
        } catch (FileNotFoundException e) {
            nameField.setFont(Font.font("Verdana", FONT_SIZE));
        }

        this.saveButton = new SpaceRunnerButton("Save results", "-fx-background-color: transparent; -fx-background-image: url(blue_button13.png);",
                "-fx-background-color: transparent; -fx-background-image: url(grey_button04.png);");
        saveButton.setButtonFont(16);

        setLayoutX(70);
        setLayoutY(230);
        setAlignment(Pos.CENTER);
        setSpacing(5);
        getChildren().addAll(nameField, saveButton);
    }

    public TextField getNameField() {
        return nameField;
    }

    public SpaceRunnerButton getSaveButton() {
        return saveButton;
    }
}
