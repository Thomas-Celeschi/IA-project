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
    private Noeud firtStep;


    public void createTaquin(String filename) throws IOException {
        Scanner scanner = new Scanner(new File(filename));
        List<String> listInitials = new ArrayList<>();
        List<String> listFinals = new ArrayList<>();
        if (scanner.hasNext()) {
            nbRow = Integer.parseInt(scanner.nextLine());
        } else {
            System.out.println("fichier vide");
        }
        for (int i = 0; i < nbRow; i++) {
            String nextLine = scanner.nextLine();
            if (nextLine.length() != nbColumn) {
                listInitials.add(nextLine + " ");
            }
        }
        for (int i = 0; i < nbRow; i++) {
            String nextLine = scanner.nextLine();
            if (nextLine.length() != nbColumn) {
                listFinals.add(nextLine + " ");
            }
        }
        nbColumn = listInitials.get(0).length() - 1;
        etatInitial = new char[nbRow][nbColumn];
        etatFinal = new char[nbRow][nbColumn];
        for (int row = 0; row < nbRow; ++row) {
            for (int column = 0; column < nbColumn; ++column) {
                etatInitial[row][column] = listInitials.get(row).charAt(column);
                etatFinal[row][column] = listFinals.get(row).charAt(column);
            }
        }
        firtStep = new Noeud(etatInitial, this);
//        open.add(firtStep);
    }

    public void readTaquin() {
        System.out.println("Taquin à l'état initial : ");
        for (int row = 0; row < nbRow; ++row) {
            for (int column = 0; column < nbColumn; ++column) {
                System.out.print(etatInitial[row][column]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Taquin à l'état final : ");
        for (int row = 0; row < nbRow; ++row) {
            for (int column = 0; column < nbColumn; ++column) {
                System.out.print(etatFinal[row][column]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("nbRow : " + nbRow);
        System.out.println("nbCol : " + nbColumn + "\n");
    }

    public int getNbRow() {
        return nbRow;
    }

    public int getNbColumn() {
        return nbColumn;
    }

    public char[][] getEtatInitial() {
        return etatInitial;
    }

    public char[][] getEtatFinal() {
        return etatFinal;
    }

    public Noeud getFirtStep() {
        return firtStep;
    }
}
