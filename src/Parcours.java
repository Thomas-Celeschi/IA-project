import java.util.*;

public abstract class Parcours {

    protected Taquin taquin;
    protected int cptVisite = 0;
    protected List<Noeud> closed = new ArrayList<>();
    protected Noeud firtStep;
    protected Noeud solution;
    protected Heuristique heuristique;

    public Parcours(Taquin taquin) {
        this.taquin = taquin;
        firtStep = new Noeud(taquin.getEtatInitial(), taquin);
    }

    public boolean check(Noeud noeud) {
        return Arrays.deepEquals(taquin.getEtatFinal(), noeud.getNoeud());
    }

    public void readOpen() {
        for(Noeud noeud : closed) {
            System.out.println("Position : " + solution.getNbParcours());
            noeud.readActualStep();
        }
    }

    public void readSolution() {
        solution = start();
        if(solution == null) {
            System.out.println("Pas de solution pour ce taquin");
        } else {
            System.out.println(solution.getNbParcours());
//            while(solution.getPere().isPresent()) {
//                System.out.println("Position : " + solution.getNbParcours());
//                solution.readActualStep();
//
//                solution = solution.getPere().get();
//            }
//            System.out.println("Position : " + solution.getNbParcours());
//            solution.readActualStep();
        }
    }

    public List<Noeud> removeOpenVisited(List<Noeud> nexts) {
        List<Noeud> toRemove = new ArrayList<>();
        for(Noeud noeud : closed) {
            for(Noeud next : nexts) {
                if(Arrays.deepEquals(noeud.getNoeud(), next.getNoeud())) {
                    toRemove.add(next);
                }
            }
        }
        return toRemove;
    }

    public void setHeuristique (Heuristique heuristique) {
        this.heuristique = heuristique;
    }

    protected abstract Noeud start();
}
