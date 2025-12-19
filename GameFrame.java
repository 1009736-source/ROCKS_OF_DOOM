import javax.swing.*;//This is where I make the frame of my game, the layout of 500 by 700
public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("ROCKS_OF_DOOM");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setContentPane(new LoginPanel(this));
        setVisible(true);
    }
    public void startGame(String username) {
        setContentPane(new GamePanel(username));
        revalidate();
    }
}