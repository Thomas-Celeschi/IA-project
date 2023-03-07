import java.util.*;

public abstract class Parcours {

    protected Taquin taquin;
    protected boolean isFinish = false;
    protected Map<Noeud, Integer> dateVisite = new HashMap<>();
    protected int cptVisite;

    public Parcours(Taquin taquin) {
        this.taquin = taquin;
        cptVisite = 0;
    }

    public boolean check(Noeud noeud) {
        if(Arrays.deepEquals(taquin.getEtatFinal(), noeud.getNoeud())) {
            isFinish = true;
            return true;
        }
        return false;
    }

    protected abstract void start();

    public void incrementCptVisite() {
        ++cptVisite;
    }
}
