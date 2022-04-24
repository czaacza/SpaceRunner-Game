package model;

public enum SHIP {
    INVADER("file:src/main/resources/view/shipchooser/playerShip1_blue.png"),
    GUARDIAN("file:src/main/resources/view/shipchooser/playerShip2_blue.png"),
    ROGUE("file:src/main/resources/view/shipchooser/playerShip3_blue.png"),
    UFO("file:src/main/resources/view/shipchooser/ufoBlue.png");

    private final String shipURL;

    private SHIP(String shipURL){
        this.shipURL = shipURL;
    }

    public String getShipURL() {
        return shipURL;
    }
}
