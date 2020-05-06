import java.util.ArrayList;
import java.util.List;


public class Player {
    private Integer myTurn;
    private List<Mutare> mutari=new ArrayList<>();
    private String name;

    public Player(Integer myTurn) {
        this.myTurn = myTurn;
    }

    public Boolean adaugaMutare(Integer linie,Integer coloana){
        ///adauga in mutari - mutarea

        Mutare mutare=new Mutare(linie,coloana);
        mutari.add(mutare);
        if(verificareCastigare(linie, coloana))
            return true;
        return false;
    }

    public Boolean verificareCastigare(Integer linie,Integer coloana){
        int i, start=0,sfarsit=0;
        Mutare mutare=new Mutare(linie,coloana);
        ///linie verticala - sus
        for( i=linie;i>=0;i--)
        {
            mutare.setColoana(coloana);
            mutare.setLinie(i);

            if(mutari.contains(mutare))
            start=i;
            else break;

        }
        for( i=linie;i<15;i++)
        {
            mutare.setColoana(coloana);
            mutare.setLinie(i);

            if(mutari.contains(mutare))
                sfarsit=i;
            else break;

        }

        if(sfarsit-start+1>=5)
            return true;

        sfarsit=start=0;
        for( i=coloana;i>=0;i--)
        {
            mutare.setColoana(i);
            mutare.setLinie(linie);

            if(mutari.contains(mutare))
                start=i;
            else break;

        }
        for( i=coloana;i<15;i++)
        {
            mutare.setColoana(i);
            mutare.setLinie(linie);

            if(mutari.contains(mutare))
                sfarsit=i;
            else break;

        }
        if(sfarsit-start+1>=5)
            return true;

        sfarsit=start=0;
        int j;
        for(j=linie,i=coloana;i>=0&&j>=0;i--,j--)
        {
            mutare.setColoana(i);
            mutare.setLinie(j);

            if(mutari.contains(mutare))
                start++;
            else break;

        }
        for(j=linie,i=coloana;i<15&&j<15;i++,j++)
        {
            mutare.setColoana(i);
            mutare.setLinie(j);

            if(mutari.contains(mutare))
                sfarsit++;
            else break;

        }
        if(sfarsit+start>=5)
            return true;
        return false;
    }

    public Integer getMyTurn() {
        return myTurn;
    }

    public void setMyTurn(Integer myTurn) {
        this.myTurn = myTurn;
    }

    public List<Mutare> getMutari() {
        return mutari;
    }

    public void setMutari(List<Mutare> mutari) {
        this.mutari = mutari;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
