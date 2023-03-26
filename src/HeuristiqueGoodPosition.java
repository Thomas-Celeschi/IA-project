public class HeuristiqueGoodPosition implements Heuristique{

    private final Taquin taquin;

    public HeuristiqueGoodPosition(Taquin taquin) {
        this.taquin = taquin;
    }
    @Override
    public void calculate(Noeud noeud) {
        int heuristique = (taquin.getNbRow() * taquin.getNbColumn()) - 1;
        for(int row = 0; row < taquin.getNbRow(); row++) {
            for(int col = 0; col < taquin.getNbColumn(); col++) {
                if(noeud.getNoeud()[row][col] != ' ' && noeud.getNoeud()[row][col] == taquin.getEtatFinal()[row][col]) {
                    --heuristique;
                }
            }
        }
        noeud.setHeuristique(heuristique);
    }
}
