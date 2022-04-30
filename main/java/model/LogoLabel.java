package model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LogoLabel extends Label {

    public final static String FONT_PATH = "src/main/resources/model/kenvector_future.ttf";
    public final static String BACKGROUND_IMAGE = "file:src/main/resources/view/shipchooser/blue_button13.png";
    private final static int PREF_HEIGHT = 520;
    private final static int PREF_WIDTH = 64;


    public LogoLabel(String text) {
        setPrefWidth(PREF_HEIGHT);
        setPrefHeight(PREF_WIDTH);
        setText(text);
        setWrapText(true);
        setAlignment(Pos.CENTER);


//        setLabelBackground();
        setLabelFont();
    }

    private void setLabelFont()  {
        try{
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),48 ));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 48));
        }
    }

    private void setLabelBackground(){
        BackgroundImage backgroundImage = new BackgroundImage(new Image(BACKGROUND_IMAGE, PREF_HEIGHT, PREF_WIDTH, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);

        setBackground(new Background(backgroundImage));
    }

}
