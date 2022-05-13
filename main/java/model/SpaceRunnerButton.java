package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpaceRunnerButton extends Button {


    private final String FONT_PATH = "src/main/resources/model/kenvector_future.ttf";
    private String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url(blue_button05.png);";
    private String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url(blue_button04.png);";
    private final int FONT_SIZE = 23;

    public SpaceRunnerButton(String text) {
        setText(text);
        setButtonFont(FONT_SIZE);
        setPrefWidth(190);
        setPrefHeight(49);
        setStyle(BUTTON_FREE_STYLE + " -fx-background-repeat: no-repeat no-repeat; -fx-background-size: cover, auto;");
        initializeButtonListener();
    }

    public SpaceRunnerButton(String text, String BUTTON_FREE_STYLE, String BUTTON_PRESSED_STYLE) {
        this.BUTTON_FREE_STYLE = BUTTON_FREE_STYLE;
        this.BUTTON_PRESSED_STYLE = BUTTON_PRESSED_STYLE;

        setText(text);
        setButtonFont(FONT_SIZE);
        setPrefWidth(190);
        setPrefHeight(49);
        setStyle(BUTTON_FREE_STYLE + " -fx-background-repeat: no-repeat no-repeat; -fx-background-size: cover, auto;");
        initializeButtonListener();
    }


    protected void setButtonFont(int FONT_SIZE) {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), FONT_SIZE));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", FONT_SIZE));
        }
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE + " -fx-background-repeat: no-repeat no-repeat; -fx-background-size: cover, auto;");
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE + " -fx-background-repeat: no-repeat no-repeat; -fx-background-size: cover, auto;");
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListener() {

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }

            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });

    }
}
