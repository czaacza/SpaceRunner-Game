package view;

import javafx.animation.Transition;
import javafx.util.Duration;

public class ShipTransition extends Transition {
    private double angle;

    public ShipTransition(Duration duration, double v, double angle) {
        super(v);
        setCycleDuration(duration);
        this.angle = angle;
    }

    @Override
    protected void interpolate(double v) {
        angle += v;
    }
}
