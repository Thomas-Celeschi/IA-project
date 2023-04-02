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

        for(int i = 0; i < nbRow; i++) {
            String nextLine = scanner.nextLine();
            System.out.println(nextLine);
            listInitials.add(nextLine);
        }

        for(int i = 0; i < nbRow; i++) {
            String nextLine = scanner.nextLine();
            listFinals.add(nextLine);
        }


        for(String s : listInitials) {
            if(s.length() > nbColumn) {
                nbColumn = s.length();
            }
        }

        etatInitial = new char[nbRow][nbColumn];
        etatFinal = new char[nbRow][nbColumn];

        for (int row = 0; row < nbRow; ++row) {
            for (int column = 0; column < nbColumn; ++column) {
                if(listInitials.get(row).length() < nbColumn && column+1 == nbColumn) {
                    etatInitial[row][column] = ' ';
                } else {
                    etatInitial[row][column] = listInitials.get(row).charAt(column);
                }
                if(listFinals.get(row).length() < nbColumn && column+1 == nbColumn) {
                    etatFinal[row][column] = ' ';
                } else {
                    etatFinal[row][column] = listFinals.get(row).charAt(column);
                }
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

        if(isSoluble()) {
            test:while (true) {

                System.out.println("1: DPF, 2: BF, 3: MD, autre: exit");
                int parcoursInt = scanner.nextInt();

                Parcours parcours;

                switch (parcoursInt) {
                    case 1 -> parcours = new Longueur(this);
                    case 2 -> parcours = new Largeur(this);
                    case 3 -> parcours = new Meilleur(this);
                    default -> {
                        break test;
                    }
                }

                System.out.println("1: sans, 2: Distance, 3: Bonne Position, autre: exit");
                int heuristiqueInt = scanner.nextInt();


                switch (heuristiqueInt) {
                    case 1 -> parcours.setHeuristique(null);
                    case 2 -> parcours.setHeuristique(new HeuristiqueDistancePosition(this));
                    case 3 -> parcours.setHeuristique(new HeuristiqueGoodPosition(this));
                    default -> {
                        break test;
                    }
                }

                System.out.println("Comment de temps en minutes, voulez vous que l'éxécution dure au maximum ?");

                int time = scanner.nextInt();
                parcours.readSolution(time);

                System.out.println("Voulez-vous tester un autre parcours ?");
            }
        } else {
            System.out.println("Ce taquin n'a pas de solution");
        }


    }

    public boolean isSoluble() {
        List<Character> debut = new ArrayList<>();
        List<Character> fin = new ArrayList<>();
        int nbDeplacement = 0;
        int deplacementVide = 0;

        int debutVideRow = 0;
        int debutVideCol = 0;
        int finVideRow = 0;
        int finVideCol = 0;

        for(int i = 0; i < nbRow; i++) {
            for(int j = 0; j <nbColumn; j++) {
                if(etatInitial[i][j] == ' ') {
                    debutVideRow = i;
                    debutVideCol = j;
                }
                if(etatFinal[i][j] == ' ') {
                    finVideRow = i;
                    finVideCol = j;
                }
                debut.add(etatInitial[i][j]);
                fin.add(etatFinal[i][j]);
            }
        }

        deplacementVide = Math.abs(debutVideRow - finVideRow) + Math.abs(debutVideCol - finVideCol);

        for(int j = debut.size()-1; j > 0; j--) {
            if(debut.get(j) != fin.get(j)) {
                int index = debut.indexOf(fin.get(j));
                Collections.swap(debut, j, index);
                nbDeplacement++;
            }
        }

        return nbDeplacement % 2 == deplacementVide % 2;
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
