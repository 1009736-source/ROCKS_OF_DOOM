import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.File;
//The core of my code is here
//The drawing and updating is handled here
//The mouse input is handled here as well
//Actionlisener is here to respond to the timed eventts in my code
//mousemotionlistener is here to track my mouse's movement and input
public class GamePanel extends JPanel implements ActionListener, MouseMotionListener {
    private Player player;
    private ArrayList<Meteor> meteors;
    private Timer timer;
    private Random rand;
    private int level = 1;
    private int score = 0;
    private String user;
    private Image background, cup;
    private boolean won = false;
    public GamePanel(String username) {//constructor for this class
        user = username;
        setFocusable(true);
        addMouseMotionListener(this);
        player = new Player();
        meteors = new ArrayList<>();
        rand = new Random();
        loadImages();
        timer = new Timer(16, this);
        timer.start();
    }
    private void loadImages() {
        try {
            background = ImageIO.read(new File("background" + level + ".jpg"));//This is how I load my background images based on what level the user is on
            cup = ImageIO.read(new File("cup.jpg"));//This is for the winning screen
        } catch (Exception e) {}
    }
    public void actionPerformed(ActionEvent e) {
        if (!won) {
            if (rand.nextInt(20 - level) == 0) {
                MeteorType t = MeteorType.NORMAL;//This spawns in the meteors at random and the type of meteors and the difficulty of those meteors is based on what level the user is on
                if (level > 1 && rand.nextInt(5) == 0) t = MeteorType.HEALING;
                if (level > 3 && rand.nextInt(4) == 0) t = MeteorType.HARMFUL;
                meteors.add(new Meteor(rand.nextInt(470), level + 2, t));
            }
            for (int i = 0; i < meteors.size(); i++) {
                Meteor m = meteors.get(i);
                m.update();
                if (player.getBounds().intersects(m.getBounds())) {
                    player.addLives(m.lifeEffect());
                    meteors.remove(i);
                    if (player.getLives() <= 0) {
                        resetGame();
                        return;
                    }
                } else if (m.offScreen()) {
                    meteors.remove(i);
                }
            }
            score++;
            if (score > 800) levelUp();//this tell you when you beat the level
        }
        repaint();//needed to redraw the screen
    }
    private void levelUp() {//the levelup command seen earleir is made here
        level++;
        score = 0;
        meteors.clear();
        if (level > 7) {
            won = true;//this tells the user he won once he completed all the levels
            timer.stop();
            StatsManager.saveStats(user, player.getLives());//This is where the users preformance is stored here
        } else {
            loadImages();
        }
    }
    private void resetGame() { //when you lose all live's this class is called
        level = 1;
        score = 0;
        player.resetLives();
        meteors.clear();
        loadImages();
    }
    public void paintComponent(Graphics g) {//this is where things get painted
        super.paintComponent(g);
        g.drawImage(background, 0, 0, 500, 700, null);
        if (!won) {
            player.draw(g);
            for (Meteor m : meteors) m.draw(g);

            g.setColor(Color.WHITE);
            g.drawString("Level: " + level, 20, 20);
            g.drawString("Lives: " + player.getLives(), 20, 40);
        } else {
            g.drawImage(cup, 150, 250, 200, 200, null);
            g.setColor(Color.WHITE);
            g.drawString("METEOR CUP EARNED!", 160, 200);//you will see this when you beat all 7 levels
        }
    }
    public void mouseMoved(MouseEvent e) {//this is how the user moves based on the user's mouse movement
        player.moveTo(e.getX(), e.getY());
    }
    public void mouseDragged(MouseEvent e) {}
}