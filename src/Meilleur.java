import java.util.*;
import java.util.concurrent.TimeUnit;

public class Meilleur extends Parcours {

    private final List<Noeud> open = new LinkedList<>();

    public Meilleur(Taquin taquin) {
        super(taquin);
    }

    @Override
    protected Noeud start(int time) {

        long time1 = System.nanoTime();
        long time2;

        if(heuristique == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("L'heuristique ne peut être null pour le meilleur d'abord");
            System.out.println("1: Distance, 2: Bonne Position");
            int heuristiqueInt = scanner.nextInt();

            if (heuristiqueInt == 1) {
                setHeuristique(new HeuristiqueDistancePosition(taquin));
            } else if (heuristiqueInt == 2) {
                setHeuristique(new HeuristiqueGoodPosition(taquin));
            }
        }

        Noeud actualNoeud = firtStep;
        closed.add(actualNoeud);
        actualNoeud.setNbParcours(cptVisite++);

        while (!check(actualNoeud)) {
            List<Noeud> nexts = actualNoeud.generateNext();
            nexts.removeAll(removeOpenVisited(nexts));

            for(Noeud noeud : nexts) {
                heuristique.calculate(noeud);
            }

            nexts.removeAll(removeClosedVisited(nexts));
            open.addAll(nexts);
            open.sort(new Comparable());
            actualNoeud = open.remove(0);
            closed.add(actualNoeud);
            assert actualNoeud != null;
            actualNoeud.setNbParcours(cptVisite++);

            time2 = System.nanoTime();

            if(time2 - time1 >= (long) time *60*1000000000) {
                System.out.println("La résolution de ce taquin avec l'algorithme MD est trop longue \n");
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
                    if(noeud.getHeuristique() > next.getHeuristique()) {
                        toRemove.add(noeud);
                    } else {
                        toRemove.add(next);
                    }
                }
            }
        }
        return toRemove;
    }
}
