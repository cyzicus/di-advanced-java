package generics;

import java.awt.*;

public class Glove implements Sized, Colored {

    private int size;
    private Color color;

    public Glove(int size, Color color) {
        this.size = size;
        this.color = color;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
