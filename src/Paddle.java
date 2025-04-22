import java.awt.*;

public class Paddle {
    int x, y, width, height;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(int dx) {
        x += dx;
        if (x < 0) x = 0;
        if (x + width > 600) x = 600 - width;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
}
