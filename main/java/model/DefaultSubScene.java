package model;


import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class DefaultSubScene extends SpaceRunnerSubScene{

    private final static String DEFAULT_BACKGROUND_IMAGE = "src/main/resources/model/blue.png";

    public DefaultSubScene() {
        super();
        this.setSubSceneBackground(DEFAULT_BACKGROUND_IMAGE, 600, 400);
        this.moveSubScene();
//        this.getPane().setStyle("-fx-border-color: #1EA7E1; -fx-border-width: 2px");

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(70);

        LogoLabel titleLabel = new LogoLabel("SPACE RUNNER");
        AuthorLabel authorLabel = new AuthorLabel(" Author:\nczaacza");

        vbox.setLayoutX(45);
        vbox.setLayoutY(70);

        vbox.getChildren().add(titleLabel);
        vbox.getChildren().add(authorLabel);

        getPane().getChildren().add(vbox);
    }

}
