import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Taquin {
    private int nbRow = 0;
    private int nbColumn = 0;
    private char[][] etatInitial;
    private char[][] etatFinal;
    public void createTaquin(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        List<String> listInitials = new ArrayList<>();
        List<String> listFinals = new ArrayList<>();
        if(scanner.hasNext()) {
            nbRow = Integer.parseInt(scanner.nextLine());
        } else {
            System.out.println("fichier vide");
        }
        for(int i = 0; i < nbRow; i++) {
            listInitials.add(scanner.nextLine());
        }
        for (int i = 0; i < nbRow; i++) {
            String nextLine = scanner.nextLine();
            if(nextLine.length() != nbColumn) {
                listFinals.add(nextLine + " ");
            }
        }
        nbColumn = listInitials.get(0).length();
        etatInitial = new char[nbRow][nbColumn];
        etatFinal = new char[nbRow][nbColumn];
        for(int row = 0; row < nbRow; ++row) {
            for (int column = 0; column < nbColumn; ++column) {
                etatInitial[row][column] = listInitials.get(row).charAt(column);
                etatFinal[row][column] = listFinals.get(row).charAt(column);
            }
        }

    }

    public void readTaquin() {
        System.out.println("Taquin à l'état initial : ");
        for(int row = 0; row < nbRow; ++row) {
            for (int column = 0; column < nbColumn; ++column) {
                System.out.print(etatInitial[row][column]);
            }
            System.out.println();
        }
        System.out.println("Taquin à l'état final : ");
        for(int row = 0; row < nbRow; ++row) {
            for (int column = 0; column < nbColumn; ++column) {
                System.out.print(etatFinal[row][column]);
            }
            System.out.println();
        }
    }

    public void findEmpty() {
        for(int row = 0; row < nbRow; ++row) {
            for (int column = 0; column < nbColumn; ++column) {
                if(etatInitial[row][column] == ' ') {
                    // TODO faire une position
                }
            }
        }
    }
}
