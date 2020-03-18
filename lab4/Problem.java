import com.github.javafaker.Faker;

import java.util.*;

public class Problem {
    public Map<Resident, List<Hospital>> rezidentiSpitale=new HashMap<>();
    public Map<Hospital,List<Resident>> spitaleRezidenti=new TreeMap<>();


    public void GenerateInput(){

        /**
         *
        //generates random 2 no.
        //the no. of Residents and the NO. of Hospitals
        //then continue to generate and create all the objects
         //generates the maps of residents and hospitals
        */

        Integer noResidents=(int)(Math.random()*100);
        Integer noHospitals=(int)(Math.random()*100);

        Faker faker = new Faker();

        List<Resident> rezidenti=new ArrayList<>();

        for(int i=0;i<noResidents;i++)
        {
            Resident rez0=new Resident(faker.name().fullName());
            rezidenti.add(rez0);

        }

        List<Hospital> spitale = new ArrayList<>();//TreeSet<>();

        for(int i=0;i<noHospitals;i++){

            Hospital spital1=new Hospital((int)(Math.random()*100),faker.address().city() + " Hospital");
            spitale.add(spital1);


        }


        for(int i=0;i<noResidents;i++)
        {
            List<Hospital> list= new ArrayList<>();

            Integer nr=(int)(Math.random()*100)%10+1;

            for(int ii=1;ii<=nr;ii++)
            {

                Integer nrRandom=(int)(Math.random()*100)%noHospitals;
                if(!list.contains(spitale.get(nrRandom)))
                list.add(spitale.get(nrRandom));
                else ii--;

            }

            rezidentiSpitale.put(rezidenti.get(i),list);



        }

        for(int i=0;i<noHospitals;i++)
        {
            List<Resident> list= new ArrayList<>();

            Integer nr=(int)(Math.random()*100)%10+1;

            for(int ii=1;ii<=nr;ii++)
            {

                Integer nrRandom=(int)(Math.random()*100)%noResidents;
                if(!list.contains(rezidenti.get(nrRandom)))
                    list.add(rezidenti.get(nrRandom));
                else ii--;

            }

            spitaleRezidenti.put(spitale.get(i),list);



        }









    }


}
