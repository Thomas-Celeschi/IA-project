import java.io.File;
import java.io.IOException;
import java.util.*;

public class Taquin {
    private int nbRow = 0;
    private int nbColumn = 0;
    private char[][] etatInitial;
    private char[][] etatFinal;


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
    }

    public void start() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Quel fichier voulez-vous tester ?");
        String file = scanner.next();

        createTaquin("src/file/" + file + ".txt");

        readTaquin();

        while (true) {

            System.out.println("1: DPF, 2: BF, 3: MD, autre: exit");
            int parcoursInt = scanner.nextInt();

            Parcours parcours;

            if (parcoursInt == 1) {
                parcours = new Longueur(this);
            } else if (parcoursInt == 2) {
                parcours = new Largeur(this);
            } else if (parcoursInt == 3) {
                parcours = new Meilleur(this);
            } else {
                break;
            }

            System.out.println("0: sans, 1: Distance, 2: Bonne Position, autre: exit");
            int heuristiqueInt = scanner.nextInt();

            if (heuristiqueInt == 0) {
                parcours.setHeuristique(null);
            } else if (heuristiqueInt == 1) {
                parcours.setHeuristique(new HeuristiqueDistancePosition(this));
            } else if (heuristiqueInt == 2) {
                parcours.setHeuristique(new HeuristiqueGoodPosition(this));
            } else {
                break;
            }

            parcours.readSolution();

            System.out.println("Voulez-vous tester un autre parcours ?");
        }
    }

    public int getPossibilities() {
        int possibilities = 1;
        for(int i = 1; i <= nbColumn * nbRow; i++) {
            possibilities = possibilities * i;
        }
        return possibilities / 2;
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

}
