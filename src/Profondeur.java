import java.util.ArrayList;
import java.util.List;

public class Profondeur extends Parcours{

    private List<Noeud> open = new ArrayList<>();

    private List<Noeud> closed = new ArrayList<>();

    public Profondeur(Taquin taquin) {
        super(taquin);
    }

    @Override
    protected void start() {
        Noeud actualNoeud = taquin.getFirtStep();
        open.add(actualNoeud);
        dateVisite.put(actualNoeud, cptVisite);
        incrementCptVisite();

        while(!isFinish) {
            List<Noeud> nexts = actualNoeud.generateNext();
            closed.addAll(nexts);
            for(Noeud noeud : nexts) {
                visiteNext();
            }
        }
    }

    public void visiteNext() {

    }
}
