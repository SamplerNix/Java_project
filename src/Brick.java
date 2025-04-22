import java.awt.*;

public class Brick {
    int x, y, width, height;
    boolean broken;

    public Brick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.broken = false;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }
}
