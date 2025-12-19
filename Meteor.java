import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
//The meteors are made here
public class Meteor extends GameObject {
    private MeteorType type;
    private BufferedImage image;
    public Meteor(int x, int speed, MeteorType type) {
        this.x = x;
        this.y = 0;
        this.speed = speed;
        this.type = type;
        width = 25;
        height = 25;
        try {//This is where the meteortype enum class is used, the 3 different types of meteors are made here
            if (type == MeteorType.HEALING)
                image = ImageIO.read(new File("green_apple.jpg"));
            else if (type == MeteorType.HARMFUL)
                image = ImageIO.read(new File("red_apple.jpg"));
            else
                image = ImageIO.read(new File("plum.jpg"));
        } catch (Exception e) {}
    }
    @Override
    public void update() {
        y += speed;
    }
    public boolean offScreen() {
        return y > 700;
    }
    public int lifeEffect() {//This is where 2 of the meteors get's its differenciating features
        if (type == MeteorType.HEALING) return 1;
        if (type == MeteorType.HARMFUL) return -2;
        return -1;
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);//we need this to draw the meteor at its current position
    }
}