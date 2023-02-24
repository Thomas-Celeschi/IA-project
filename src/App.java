import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        Taquin taquin = new Taquin();
        taquin.createTaquin("src/file/2x4c.txt");
        taquin.readTaquin();
    }
}
