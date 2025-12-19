import java.io.*;//This is where I store the user's preformance during the game into a file
public class StatsManager {
    public static void saveStats(String user, int lives) {
        try (FileWriter fw = new FileWriter("stats.txt", true)) {
            fw.write(user + " beat ROCKS_OF_DOOM with " + lives + " lives left\n");
        } catch (Exception e) {}//Used a lot in my code, just used to ignore errors
    }
}