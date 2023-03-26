import java.util.*;

public class Largeur extends Parcours{

    private final Queue<Noeud> closed = new ArrayDeque<>();

    public Largeur(Taquin taquin) {
        super(taquin);
    }

    @Override
    protected Noeud start() {
        Noeud actualNoeud = firtStep;
        open.add(actualNoeud);
        actualNoeud.setNbParcours(cptVisite++);

        while (!check(actualNoeud)) {

            List<Noeud> nexts = actualNoeud.generateNext();
            nexts.removeAll(removeOpenVisited(nexts));
            nexts.removeAll(removeClosedVisited(nexts));

            if(heuristique != null) {
                for(Noeud noeud : nexts) {
                    heuristique.calculate(noeud);
                }

                nexts.sort(new Comparable());

            }

            closed.addAll(nexts);
            actualNoeud = closed.poll();
            open.add(actualNoeud);
            assert actualNoeud != null;
            actualNoeud.setNbParcours(cptVisite++);

            if(cptVisite == taquin.getPossibilities()) {
                return null;
            }
        }

        return actualNoeud;
    }
    public List<Noeud> removeClosedVisited(List<Noeud> nexts) {
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

    public void setHeuristique(Heuristique heuristique) {
        this.heuristique = heuristique;
    }
}
