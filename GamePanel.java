package Java_project;

import Java_project.Paddle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private final int WIDTH = 600;
    private final int HEIGHT = 500;
    private Timer timer;//Runs a game loop every few milliseconds
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private boolean play = true;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));//building window
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(this);
        setupGame();
        timer = new Timer(10, this);
        timer.start();
    }
    public void resetGame() {
        setupGame();
        play = true;
        timer.start();
    }


    public void setupGame() {
        paddle = new Paddle(WIDTH / 2 - 50, HEIGHT - 50, 100, 10);
        ball = new Ball(WIDTH / 2, HEIGHT / 2, 15, -2, -3);//15  is diameter X=-2(UP),y=-3(left) moves with velocity
        bricks = new ArrayList<>();//Prepares a brand new list to hold fresh bricks

        int rows = 5, cols = 10;
        int brickWidth = 50, brickHeight = 20;
        for (int row = 0; row < rows; row++) {//creating a grid of bricks
            for (int col = 0; col < cols; col++) {
                bricks.add(new Brick(55 * col + 30, 30 + row * 25, brickWidth, brickHeight));// it is adding with specfic gap
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paddle.draw(g);
        ball.draw(g);
        for (Brick brick : bricks) {
            if (!brick.isBroken())
                brick.draw(g);
        }

        if (!play) {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over", WIDTH / 2 - 80, HEIGHT / 2);
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press Enter to Restart", WIDTH / 2 - 110, HEIGHT / 2 + 40);
        }

        if (bricks.stream().allMatch(Brick::isBroken)) {// matches every brick is broken then You win
            g.setColor(Color.green);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("You Win!", WIDTH / 2 - 80, HEIGHT / 2);
            play = false;
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play) {
            ball.move();
            ball.checkWallCollision(WIDTH, HEIGHT);
            ball.checkPaddleCollision(paddle);

            for (Brick brick : bricks) {
                if (!brick.isBroken() && ball.getRect().intersects(brick.getRect())) {
                    ball.reverseY();
                    brick.setBroken(true);
                    break;
                }
            }

            if (ball.y > HEIGHT) {
                play = false;
                timer.stop();
            }

            repaint();
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {//VK_LEFT is a constant in the KeyEvent class.
            paddle.move(-20);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.move(20);
        }
        if (!play && e.getKeyCode() == KeyEvent.VK_ENTER) {
            resetGame();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
