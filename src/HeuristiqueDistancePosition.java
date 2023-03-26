public class HeuristiqueDistancePosition implements Heuristique{

    private final Taquin taquin;

    public HeuristiqueDistancePosition(Taquin taquin) {
        this.taquin = taquin;
    }

    @Override
    public void calculate(Noeud noeud) {
        int heuristique = 0;
        for(int row = 0; row < taquin.getNbRow(); row++) {
            for(int col = 0; col < taquin.getNbColumn(); col++) {
                for(int fRow = 0; fRow < taquin.getNbRow(); fRow++) {
                    int betterCol = 0;
                    for(int fCol = 0; fCol < taquin.getNbColumn(); fCol++) {
                        betterCol = fCol;
                        if(noeud.getNoeud()[row][col] != ' ' && noeud.getNoeud()[row][col] == taquin.getEtatFinal()[fRow][fCol]) {
                            heuristique = heuristique + Math.abs(row - fRow) + Math.abs(col - fCol);
                            break;
                        }
                    }
                    if(noeud.getNoeud()[row][col] != ' ' && noeud.getNoeud()[row][col] == taquin.getEtatFinal()[fRow][betterCol]){
                        break;
                    }
                }
            }
        }
        noeud.setHeuristique(heuristique);
    }
}
