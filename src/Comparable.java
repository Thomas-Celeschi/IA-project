import java.util.Comparator;

public class Comparable implements Comparator<Noeud> {

    @Override
    public int compare(Noeud o1, Noeud o2) {
        return Integer.compare(o1.getHeuristique(), o2.getHeuristique());
    }
}
