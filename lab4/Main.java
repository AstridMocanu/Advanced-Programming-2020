import java.io.IOException;
import java.util.*;

public class Main {
    public Map<Resident,List<Hospital>> rezidentiSpitale;
    public Map<Hospital,List<Resident>> spitaleRezidenti;


    public static void main(String args[])throws IOException{
        Main main=new Main();

        List<Resident> rezidenti=new ArrayList<Resident>();



        Resident rez0=new Resident("R0");
        Resident rez1=new Resident("R1");
        Resident rez2=new Resident("R2");
        Resident rez3=new Resident("R3");

       // List<Resident> rezidenti=new ArrayList<>(List.of(rez0,rez1,rez2,rez3));

        rezidenti.add(rez2);
        rezidenti.add(rez1);
        rezidenti.add(rez3);
        rezidenti.add(rez0);


        rezidenti.sort(Comparator.comparing(Resident::getName));

        Hospital spital1=new Hospital(1,"H0");
        Hospital spital2=new Hospital(2,"H1");
        Hospital spital3=new Hospital(2,"H2");




        Set<Hospital> spitale = new TreeSet<>();

        spitale.add(spital1);
        spitale.add(spital2);
        spitale.add(spital3);



        Map<Resident,List<Hospital>> rezidentiSpitale=new HashMap<>();

        main.rezidentiSpitale=rezidentiSpitale;

        rezidentiSpitale.put(rez0,Arrays.asList(spital1,spital2,spital3));
        rezidentiSpitale.put(rez1,Arrays.asList(spital1,spital2,spital3));
        rezidentiSpitale.put(rez2,Arrays.asList(spital1,spital2));
        rezidentiSpitale.put(rez3,Arrays.asList(spital1,spital3));


        Set<Map.Entry<Resident,List<Hospital>>> st=rezidentiSpitale.entrySet();

        for(Map.Entry<Resident,List<Hospital>> i:st)
            System.out.println(i.getKey()+" "+i.getValue());

        System.out.println();



        for(Map.Entry<Resident,List<Hospital>> i:st)
        {

            System.out.println(i.getValue().contains(spital1)&&i.getValue().contains(spital2)?i.getKey():"");

        }



        Map<Hospital, List<Resident>> spitaleRezidenti= new TreeMap<Hospital, List<Resident>>();

        main.spitaleRezidenti=spitaleRezidenti;

        spitaleRezidenti.put(spital1,Arrays.asList(rez3,rez0,rez1,rez2));
        spitaleRezidenti.put(spital2,Arrays.asList(rez0,rez2,rez1));
        spitaleRezidenti.put(spital3,Arrays.asList(rez0,rez1,rez3));


        Set<Map.Entry<Hospital,List<Resident>>> st2=spitaleRezidenti.entrySet();

        System.out.println();

        for(Map.Entry<Hospital,List<Resident>> i:st2)
            System.out.println(i.getKey()+" "+i.getValue());

        System.out.println();

        for(Map.Entry<Hospital,List<Resident>> i:st2) {
            System.out.println(i.getValue().contains(rez0)?i.getKey():"nothing");


        }

        System.out.println();

        /*
        Collection<Hospital> col1=new ArrayDeque<>();

        col1.add(spital1);
        col1.add(spital2);

        System.out.println(spitale.stream().filter(sp -> spitaleRezidenti.get(sp).containsAll(col1)).findFirst());
*/



        ///////Optional


        Problem problem=new Problem();

        problem.GenerateInput();
        main.rezidentiSpitale=problem.rezidentiSpitale;
        main.spitaleRezidenti=problem.spitaleRezidenti;

        Algorithm algorithm=new Algorithm();
       if(algorithm.Algorithm1(main)==1)
           System.out.println("este stabil");
       else
           System.out.println(" nu este stabil");




    }

}
