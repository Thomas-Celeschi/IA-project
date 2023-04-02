
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Longueur extends Parcours {

    private final Stack<Noeud> open = new Stack<>();

    public Longueur(Taquin taquin) {
        super(taquin);
    }

    @Override
    protected Noeud start(int time) {

        long time1 = System.nanoTime();
        long time2;

        Noeud actualNoeud = firtStep;
        closed.add(actualNoeud);
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
                    open.push(nexts.get(i));
                }

            } else {
                open.addAll(nexts);
            }

            actualNoeud = open.pop();
            closed.add(actualNoeud);
            actualNoeud.setNbParcours(cptVisite++);

            time2 = System.nanoTime();

            if((time2 - time1) >= (long) time*60*1000000000) {
                System.out.println("La résolution de ce taquin avec l'algorithme DPF est trop longue \n");
                return null;
            }

        }

        time2 = System.nanoTime();
        System.out.println("Temps d'execution : " + TimeUnit.NANOSECONDS.toMillis(time2 - time1) + " milisecondes");

        return actualNoeud;
    }

    public List<Noeud> removeClosedVisited(List<Noeud> nexts) {
        List<Noeud> toRemove = new ArrayList<>();
        for(Noeud noeud : open) {
            for(Noeud next : nexts) {
                if(Arrays.deepEquals(noeud.getNoeud(), next.getNoeud())) {
                    toRemove.add(next);
                }
            }
        }
        return toRemove;
    }
}
