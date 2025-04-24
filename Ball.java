package Java_project;

import Java_project.Paddle;

import java.awt.*;

public class Ball {
    int x, y, size;
    int dx, dy;

    public Ball(int x, int y, int size, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.dx = dx;
        this.dy = dy;
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics a) {
        a.setColor(Color.cyan);
        a.fillOval(x, y, size, size);//size is diameter , to make it circle height and width must be same
    }

    public void checkWallCollision(int width, int height) {
        if (x <= 0 || x + size >= width) dx *= -1;
        if (y <= 0) dy *= -1;
    }

    public void checkPaddleCollision(Paddle paddle) {
        if (getRect().intersects(paddle.getRect())) {
            dy = -dy;
        }
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, size, size);
    }

    public void reverseY() {
        dy = -dy;
    }
}
