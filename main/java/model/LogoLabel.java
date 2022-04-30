package model;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LogoLabel extends SpaceRunnerLabel {

    private final int PREF_HEIGHT = 64;
    private final int PREF_WIDTH = 550;
    private final int FONT_SIZE = 58;


    public LogoLabel(String text) {
        super(text);
        setTextFill(Paint.valueOf("#EEEEEE"));
        setPrefHeight(PREF_HEIGHT);
        setPrefWidth(PREF_WIDTH);
    }

    protected void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), FONT_SIZE));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", FONT_SIZE));
        }
    }

}
