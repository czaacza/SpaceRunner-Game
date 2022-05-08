package model;

public enum SHIP {
    INVADER("file:src/main/resources/view/shipchooser/playerShip1_blue.png", "file:src/main/resources/model/game_models/playerLife1_blue.png"),
    GUARDIAN("file:src/main/resources/view/shipchooser/playerShip2_blue.png", "file:src/main/resources/model/game_models/playerLife2_blue.png"),
    ROGUE("file:src/main/resources/view/shipchooser/playerShip3_blue.png", "file:src/main/resources/model/game_models/playerLife3_blue.png"),
    UFO("file:src/main/resources/view/shipchooser/ufoBlue.png", "file:src/main/resources/view/shipchooser/ufoBlue.png");

    private final String shipURL;
    private final String lifeURL;

    private SHIP(String shipURL, String lifeURL){
        this.shipURL = shipURL;
        this.lifeURL = lifeURL;
    }

    public String getShipURL() {
        return shipURL;
    }

    public String getLifeURL() {
        return lifeURL;
    }
}
