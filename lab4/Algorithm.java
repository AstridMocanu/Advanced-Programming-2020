import java.util.List;
import java.util.Map;
import java.util.Set;

public class Algorithm {

    ////realizarea cuplajelor OPTIONAL

    public int Algorithm1(Main main)
    {
        Matching match=new Matching();
        int ok=1;

        /**
        ///l tuturor residenti
        ///fiecarui rezident ii parcurg lista cu spitale
        //atribui un spital daca este primul spital disponibil
        //DISPONIBIL=capacitatea s-a permite,nr curent de pacienti cazati nu depaseste capacitatea
         */

        Set<Map.Entry<Resident, List<Hospital>>> st=main.rezidentiSpitale.entrySet();

        for(Map.Entry<Resident,List<Hospital>> i:st)
        {
            for(Hospital j:i.getValue())
                if(j.getCurrentNo()<j.getCapacity())
                {
                    if(!match.isStable(main,i.getKey(),j))
                        ok=0;
                    System.out.println(i.getKey().getName()+" "+j.getName());
                    j.setCurrentNo(j.getCurrentNo()+1);
                    break;

                }
            else break;


        }

        return ok;



    }



    ///realizare cuplaje BONUS----ALGORITHM2

}
