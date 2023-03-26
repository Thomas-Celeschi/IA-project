
import java.util.*;

public class Longueur extends Parcours {

    private final Stack<Noeud> closed = new Stack<>();

    public Longueur(Taquin taquin) {
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
                for(int i = nexts.size() - 1; i >= 0; i--) {
                    closed.push(nexts.get(i));
                }

            } else {
                closed.addAll(nexts);
            }

            actualNoeud = closed.pop();
            open.add(actualNoeud);
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
}
