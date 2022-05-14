package model;

import javafx.scene.paint.Paint;

public class LogoLabel extends SpaceRunnerLabel {

    private final int PREF_HEIGHT = 64;
    private final int PREF_WIDTH = 550;
    private int FONT_SIZE = 58;


    public LogoLabel(String text) {
        super(text);
        setLabelFont(FONT_SIZE);
        setTextFill(Paint.valueOf("#EEEEEE"));
        setPrefHeight(PREF_HEIGHT);
        setPrefWidth(PREF_WIDTH);
    }

    public LogoLabel(String text, int FONT_SIZE) {
        super(text);
        this.FONT_SIZE = FONT_SIZE;
        setLabelFont(FONT_SIZE);
        setTextFill(Paint.valueOf("#EEEEEE"));
        setPrefHeight(PREF_HEIGHT);
        setPrefWidth(PREF_WIDTH);
    }



}
