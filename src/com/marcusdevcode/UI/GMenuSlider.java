package src.com.marcusdevcode.UI;

import src.com.marcusdevcode.Main;

import java.awt.*;

public class GMenuSlider extends GMenuButton {
    double min;
    double max;
    double step;
    public GMenuSlider(Main game, String back, Point point, double min, double max, double step) {
        super(game, back, point);
        this.min = min;
        this.max = max;
        this.step = step;
    }

    @Override
    public void loadResources() {
        super.loadResources();
    }
}
