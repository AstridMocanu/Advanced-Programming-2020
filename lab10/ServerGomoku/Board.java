import java.util.ArrayList;
import java.util.List;

public class Board {
    private Integer dimension;
    private List<Mutare> mutari=new ArrayList<>();

    public Board(Integer dimension) {
        this.dimension = dimension;
    }


    public void adaugaMutare(Integer linie,Integer coloana) {

        Mutare mutare=new Mutare(linie,coloana);
        mutari.add(mutare);
    }

    public Boolean verificaValiditateMutare(Integer linie,Integer coloana){
        //daca e libera pozitia putem pune acolo

        Mutare mutare=new Mutare(linie,coloana);
        if(mutari.size()>0) {
            System.out.println(mutari.get(mutari.size()-1).getLinie());
            System.out.println(mutari.get(mutari.size()-1).getColoana());
        }
        return !mutari.contains(mutare);
    }









    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }
}
