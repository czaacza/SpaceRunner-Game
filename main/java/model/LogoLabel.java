package model;

import javafx.animation.TranslateTransition;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

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

    public void showLogoLabel(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        transition.setToY(100);

        transition.play();
    }

    public void hideLogoLabel(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        transition.setToY(0);

        transition.play();
    }


}
