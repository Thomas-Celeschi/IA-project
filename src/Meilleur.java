import java.util.*;

public class Meilleur extends Parcours {

    private final List<Noeud> closed = new LinkedList<>();

    public Meilleur(Taquin taquin) {
        super(taquin);
    }

    @Override
    protected Noeud start() {
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
        open.add(actualNoeud);
        actualNoeud.setNbParcours(cptVisite++);

        while (!check(actualNoeud)) {
            List<Noeud> nexts = actualNoeud.generateNext();
            nexts.removeAll(removeOpenVisited(nexts));

            for(Noeud noeud : nexts) {
                heuristique.calculate(noeud);
            }

            nexts.removeAll(removeClosedVisited(nexts));
            closed.addAll(nexts);
            closed.sort(new Comparable());
            actualNoeud = closed.remove(0);
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
