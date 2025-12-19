import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
public class Player extends GameObject {
    private int lives = 3;
    private BufferedImage image;
    public Player() {
        width = 30;
        height = 40;
        try {
            image = ImageIO.read(new File("ship.jpg"));
        } catch (Exception e) {}
    }
    public void moveTo(int mx, int my) {
        x = mx - width / 2;
        y = my - height / 2;
    }
    public void addLives(int amt) {
        lives += amt;
    }
    public int getLives() {
        return lives;
    }
    public void resetLives() {
        lives = 3;
    }
    @Override
    public void update() {}
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}