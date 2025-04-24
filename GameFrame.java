package Java_project;

import Java_project.GamePanel;

import javax.swing.JFrame;
//ye game ka frame bnane ke liye hai
public class GameFrame extends JFrame {

    public GameFrame() {
        this.add(new GamePanel());
        this.setTitle("Java_project.Brick Breaker");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//when i click on cross on window it exits
        this.setResizable(false);
        this.pack();//to fit all component in the window accordingly and automatically
        this.setLocationRelativeTo(null);//center is relative to null
        this.setVisible(true);
    }
}
