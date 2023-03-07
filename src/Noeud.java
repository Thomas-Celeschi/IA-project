import java.util.ArrayList;
import java.util.List;

public class Noeud {

    private final Taquin taquin;
    private final char[][] noeud;

    private int emptyRow;
    private int emptyCol;
    private int nbParcours;
    private Integer heuristique;
    List<Noeud> nextSteps = new ArrayList<>();

    public Noeud(char[][] noeud, Taquin taquin) {
        this.noeud = noeud;
        this.taquin = taquin;
        heuristique = calculateHeuristique();
        findEmpty();
    }

    private Integer calculateHeuristique() {
        return 0;
    }

    public void findEmpty() {
        for(int row = 0; row < taquin.getNbRow(); ++row) {
            for (int column = 0; column < taquin.getNbColumn(); ++column) {
                if(noeud[row][column] == ' ') {
                    emptyRow = row;
                    emptyCol = column;
                }
            }
        }
    }


    public List<Noeud> generateNext() {
        System.out.println("emptyRow : " + emptyRow);
        System.out.println("emptyCol : " + emptyCol);
        if(emptyCol != 0) {
            generateLeft();
        }
        if(emptyRow != 0) {
            generateUp();
        }
        if(emptyCol != taquin.getNbColumn()-1) {
            generateRight();
        }
        if(emptyRow != taquin.getNbRow()-1) {
            generateDown();
        }
        return nextSteps;
    }

    private void generateLeft() {
        char[][] left = new char[taquin.getNbRow()][taquin.getNbColumn()];
        for(int row = 0; row < taquin.getNbRow(); ++row) {
            for (int column = 0; column < taquin.getNbColumn(); ++column) {
                left[row][column] = noeud[row][column];
            }
        }
        left[emptyRow][emptyCol] = left[emptyRow][emptyCol-1];
        left[emptyRow][emptyCol-1] =  ' ';
        Noeud leftTaquin = new Noeud(left, taquin);
        nextSteps.add(leftTaquin);
    }

    private void generateUp() {
        char[][] up = new char[taquin.getNbRow()][taquin.getNbColumn()];
        for(int row = 0; row < taquin.getNbRow(); ++row) {
            for (int column = 0; column < taquin.getNbColumn(); ++column) {
                up[row][column] = noeud[row][column];
            }
        }
        up[emptyRow][emptyCol] = up[emptyRow-1][emptyCol];
        up[emptyRow-1][emptyCol] =  ' ';
        Noeud upTaquin = new Noeud(up, taquin);
        nextSteps.add(upTaquin);
    }

    private void generateRight() {
        char[][] right = new char[taquin.getNbRow()][taquin.getNbColumn()];
        for(int row = 0; row < taquin.getNbRow(); ++row) {
            for (int column = 0; column < taquin.getNbColumn(); ++column) {
                right[row][column] = noeud[row][column];
            }
        }
        right[emptyRow][emptyCol] = right[emptyRow][emptyCol+1];
        right[emptyRow][emptyCol+1] = ' ';
        Noeud rightTaquin = new Noeud(right, taquin);
        nextSteps.add(rightTaquin);
    }

    private void generateDown() {
        char[][] down = new char[taquin.getNbRow()][taquin.getNbColumn()];
        for(int row = 0; row < taquin.getNbRow(); ++row) {
            for (int column = 0; column < taquin.getNbColumn(); ++column) {
                down[row][column] = noeud[row][column];
            }
        }
        down[emptyRow][emptyCol] = down[emptyRow+1][emptyCol];
        down[emptyRow+1][emptyCol] = ' ';
        Noeud downTaquin = new Noeud(down, taquin);
        nextSteps.add(downTaquin);

    }

    public void readActualStep() {
        for(int row = 0; row < taquin.getNbRow(); ++row) {
            for (int column = 0; column < taquin.getNbColumn(); ++column) {
                System.out.print(noeud[row][column]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void readNextStep() {
        for (Noeud taquin : nextSteps) {
            taquin.readActualStep();
        }
    }

    public char[][] getNoeud() {
        return noeud;
    }

    public void setNbParcours(int nbParcours) {
        this.nbParcours = nbParcours;
    }

    public List<Noeud> getNextSteps() {
        return nextSteps;
    }
}
