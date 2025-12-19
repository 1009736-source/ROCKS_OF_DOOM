import java.io.*; //this is where I store the log in info
import java.util.*;
public class UserManager {
    private static final String FILE = "users.txt";

    public static boolean login(String u, String p) {
        try (Scanner sc = new Scanner(new File(FILE))) {
            while (sc.hasNextLine()) {
                String[] d = sc.nextLine().split(",");
                if (d[0].equals(u) && d[1].equals(p)) return true;
            }
        } catch (Exception e) {}
        return false;
    }
    public static void register(String u, String p) {
        try (FileWriter fw = new FileWriter(FILE, true)) {
            fw.write(u + "," + p + "\n");//This is where I check if the password and username that was inputed by the user was stored in the file
        } catch (Exception e) {}
    }
}