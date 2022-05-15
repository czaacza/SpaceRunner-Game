package model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class SpaceRunnerLabel extends Label {
    protected final static String FONT_PATH = "src/main/resources/model/kenvector_future.ttf";
    protected int DEFAULT_FONT_SIZE = 23;

    public SpaceRunnerLabel(String text) {
        setText(text);
        setWrapText(true);
        setAlignment(Pos.CENTER);
        setLabelFont(DEFAULT_FONT_SIZE);
    }

    public void setLabelFont(int FONT_SIZE) {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), FONT_SIZE));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", FONT_SIZE));
        }
    }

}
