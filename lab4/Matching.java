import java.util.List;
import java.util.Map;
import java.util.Set;

public class Matching {



    public boolean isMatching(Main main,Resident resident,Hospital hospital){




        return true;
    }


    /** //un matching e stabil daca:
     nu exista spital pe care resident sa il prefere mai mult decat pe hospital
     SI nici nu exista rezident pe care hospital sa-l prefere mai mult decat pe resident
     AMBELE PETRECANDU-SE IN ACELASI timp
     */

    public boolean isStable(Main main,Resident resident,Hospital hospital){

        /** //pasi:
         1.se parcurg spitalele pe care resident le prefera pana ajungem la spitalul hospital
         daca niciun spital de pana atunci nu va STRICA cuplajul, atunci CUPLAJUL ESTE STABIL
         altfel
         2.se parcurg pt fiecare spital residentii in ordinea preferintei lui
         daca niciun rezident parcurs nu STRICA cuplajul, atunci CUPLAJUL ESTE STABIL
         altfel
         3.CUPLAJUL NU ESTE STABIL
         */


        Set<Map.Entry<Resident,List<Hospital>>> st=main.rezidentiSpitale.entrySet();

        for(Map.Entry<Resident,List<Hospital>> i:st)
        {
            if(i.getKey().getName().equals(resident.getName()))///am obtinut acces la lista spitalelor rezidentului resident
            {//1.
                for(Hospital j:i.getValue())
                {
                    if(j.getName().equals(hospital))
                        return true;
                    else
                    {
                        //2.
                        Set<Map.Entry<Hospital, List<Resident>>> st2=main.spitaleRezidenti.entrySet();
                        for(Map.Entry<Hospital,List<Resident>> k:st2)
                            if(k.getKey().getName().equals(j.getName()))
                            {
                                for(Resident l:k.getValue())
                                {
                                    if(l.getName().equals(resident))
                                        return true;
                                    else
                                        return false;
                                }
                            }
                    }


                }
            }


        }

        return false;
    }


}
