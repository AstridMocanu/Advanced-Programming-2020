package sample;

import java.util.*;

public class SmartPlayer extends Player {

    private Board board;
    private List<Integer> numere=new ArrayList<>();
    private Integer kapa;
    private Integer myTurn;
    private Integer[][] progresii;
    private Integer t=0;
    private Integer nrMaxTokeni=0;
    private static Random rand=new Random();
    private static Integer random;
    private static Integer randomDif;

    public SmartPlayer(String name,Board board,Integer kapa,Integer turn )
    {
        this.name = name;
        this.board = board;
        this.kapa=kapa;
        this.myTurn=turn;
        this.progresii=new Integer[board.getnumberOfTokens()*10][5];
        this.nrMaxTokeni=board.getnumberOfTokens();
        random=rand.nextInt(1000);
        random=random%10;
        randomDif=rand.nextInt(20);
    }



    @Override
    public void run() {
        System.out.println("AM INTRAT AICI "+ name);

        ///se verifica daca mai sunt piese pe tabla
        ///(trebuie citit din buffer)
        ///si apoi eliminat din buffer

        //daca mai sunt se alege un nr dupa algoritm
        //si playerul ia piesa de pe tabla


        //initializare progresii
        for(int i=1;i<board.getnumberOfTokens()*10;i++)
        {
            progresii[i][1]=0;
            progresii[i][2]=0;
            progresii[i][3]=0;
           // progresii[i][4]=1;

        }


        System.out.println("am initializat");


        ///////////////////////////////

        Integer nr;
        while(board.getMaximul()<kapa && board.getnumberOfTokens()>0)
            if(board.getTurn()==myTurn) {

                System.out.println("while "+board.getnumberOfTokens()+" " +name+" "+t);

                nr = board.getnumberOfTokens();

                Integer num;
                Integer value;

                System.out.println("aici reusim "+name);


                ///verificam vectorul 2-dimensional progresii
                //care este sortat in prealabil descrescator dupa dimensiunea progresiei corespunzatoare
                //luam prima progresie calc urmatorul termen
                //daca acesta este disponibil, il luam


                ///progresii[i][1]---lg progresiei
                ///progresii[i][2]---primul termen al progresiei
                ///progresii[i][3]---dif progresiei
                ///progresii[i][4]---disponibilitatea urmatorului termen progresiei

                System.out.println(name  + ": marimea lui progresii este "+t);

                System.out.println(Arrays.deepToString(progresii));


                for(int i=1;progresii[i][1]!=0;i++)
                    {

                        ///term initial+ dif progresie*lg ei
                        int termen=progresii[i][2]+(progresii[i][1])*progresii[i][3];
                        System.out.println(name + ": am tokenii: " + numere);
                        System.out.println(name + ": as dori sa iau numarul " + termen) ;

                        int j;

                        /*for( j=1;j<=board.getnumberOfTokens();j++)
                        {
                            if(Integer.parseInt(board.getTokeni()[j].getNr())==termen)break;
                        }

*/
                            if(termen<=nrMaxTokeni)
                            {if(board.getTokeni()[termen].isAvailableToken())
                                {


                                    board.setnumberOfTokens(board.getnumberOfTokens() - 1);

                                    System.out.println("AM INTART IN IF  "+name);

                                    board.getTokeni()[termen].setAvailableToken(false);
                                    numere.add(termen);
                                    progresii[i][1]++;

                                    System.out.println("Number consumed: " + termen);

                                }}


                    }

                ///am reusit extragerea unui nr din buffer si setarea lui ca INVALID
                ///plus
                ///adaugare nr in setul de nr al player-ului


              //  System.out.println("deci am ajuns si aici inainte de sortare"+name);


               //////////////////////////////////////////////

                ///sortare lista

                numere.sort(Integer::compareTo);

               // System.out.println("si sortez corect"+name);

                ///verificarea progresiei maxime existente

                int dif = 0, lg, max = 0, poz=0, pozdif=0;
                int player1, player2;


                ///ALGORITM VERIFICARE PROGRESIE
                //se iau 2 nr din numere
                //se calculeaza diferenta lor si se presupune existenta unei progresii pornind de la acei termeni
                //se cauta in numere fiecare termen care ar tb sa apartina progresiei
                //daca nu exista vreun termen, se compara cu maximul si se iese
                //continuam cautarea progresiei

                int breakpoint = 0;

                System.out.println(name + " : " +  numere.size());

                for (int i = 0; i <  numere.size()- 1; i++)
                    for (int j = i + 1; j <numere.size(); j++) {
                        dif = numere.get(j) - numere.get(i);
                        //System.out.println(name + " line 153: dif = " + dif);
                        lg = 2;
                     //   System.out.println("AM intreta in cele 2 foruri si am aflat dif " + dif +" "+name);

                        for (int k = numere.get(j) + dif; k <Collections.max(numere); k += dif)
                        {
                            //System.exit(1);
                            for (int y = j + 1; y <numere.size(); y++)
                            {
                                if (numere.get(y) == k)
                                {
                                    //   System.out.println("am gasit nr"+name);
                                    lg++;

                                }
                                if(y==numere.size()-1)
                                {
                                    if (lg > max) {
                                        max = lg;
                                        poz = i;
                                        pozdif = dif;
                                        System.out.println("line 172: pozdif = " + pozdif);

                                        int p=1;
                                        for(p=1;p<=t;p++)
                                            ///daca progresia exista deja, doar ii facem upgrade lg
                                            if(progresii[p][2].equals(numere.get(poz)) && progresii[p][3]==pozdif)
                                            {
                                                progresii[p][1]=lg;
                                                // System.out.println("am actualizat o progresie"+name);
                                                break;
                                            }

                                        ///altfel adaugam
                                        if(p>t){
                                            t++;
                                            progresii[t][1]=lg;
                                            progresii[t][2]=numere.get(poz);
                                            progresii[t][3]=pozdif;
//System.exit(1);
                                            //System.out.println("am scris in progresii o noua progresie"+name);


                                        }

                                        break;

                                    }
                                }
                            }
                        }


                    }




                ////////sortam progresiile

                for(int i=1;i<t;i++)
                    for(int j=i+1;j<=t;j++)
                        if(progresii[i][1]<progresii[j][1])
                        {
                            int aux;
                            aux=progresii[j][1];
                            progresii[j][1]=progresii[i][1];
                            progresii[i][1]=aux;

                            aux=progresii[j][2];
                            progresii[j][2]=progresii[i][2];
                            progresii[i][2]=aux;

                            aux=progresii[j][3];
                            progresii[j][3]=progresii[i][3];
                            progresii[i][3]=aux;



                        }

              //  System.out.println("am sortat ce progresii aveam "+name);



                ////stabilirea castigatorului


                if(max>board.getMaximul())
                    board.setMaximul(max);

                System.out.println(max+" acesta e max");

                //jocul se termina daca progresia max este de lg kapa
                //sau daca tabla este goala
                if(max>=kapa)
                {
                    if (board.getPlayer1Score() == 0)
                        board.setPlayer1Score(max);
                    else
                        board.setPlayer2Score(max);

                    player1 = board.getPlayer1Score();
                    player2 = board.getPlayer2Score();



                    if (max >= kapa) {



                        if (max % 3 == 0)
                            System.out.println("Now we have a WINNER!!\nWhat's your name,son?\n" + name);

                        if (max % 3 == 1)
                            System.out.println("Winner, winner...of the Board!\nWho's the best player of them all?\n" + name);

                        if (max % 3 == 2)
                            System.out.println("Good judgment comes from bad experience, and a lot of that comes from bad judgment\nWell done " + name);

                        for(int i=numere.get(poz), contor=1;contor<=kapa;i+=pozdif,contor++)
                            System.out.println(i+ " ");

                        System.out.println();
                        //System.out.println(numere);


                    } else {
                        if (max == player1 && max < player2)
                            System.out.println("If at first you don’t succeed, skydiving is not for you.");

                        else if (max == player1)
                            if (max % 3 == 0)
                                System.out.println("Now we have a WINNER!!\nWhat's your name,son?\n" + name);

                        if (max % 3 == 1)
                            System.out.println("Winner, winner...of the Board!\nWho's the best player of them all?\n" + name);

                        if (max % 3 == 2)
                            System.out.println("Good judgment comes from bad experience, and a lot of that comes from bad judgment\nWell done " + name);


                    }}



                ///extragere initiala

                if(board.getTurn().equals(myTurn))
                if(t<2){

                    if(board.getTokeni()[1].isAvailableToken())
                    {
                        System.out.println("token1:sunt pe tura lui "+name);
                        board.setnumberOfTokens(board.getnumberOfTokens() - 1);

                        board.getTokeni()[1].setAvailableToken(false);
                        numere.add(Integer.parseInt(board.getTokeni()[1].getNr()));

                        t++;
                        progresii[1][1]=1;
                        progresii[1][2]=1;
                        progresii[1][3]=0;



                        System.out.println("Number consumed: " + Integer.parseInt(board.getTokeni()[1].getNr())+" "+name);


                    }
                    else
                    if(board.getTokeni()[2].isAvailableToken())
                    {


                        System.out.println("token2:iar eu pe tura lui "+name);
                        board.setnumberOfTokens(board.getnumberOfTokens() - 1);

                        t++;
                        progresii[2][1]=1;
                        progresii[2][2]=2;
                        progresii[2][3]=0;



                        board.getTokeni()[2].setAvailableToken(false);
                        numere.add(Integer.parseInt(board.getTokeni()[2].getNr()));


                        System.out.println("Number consumed: " + Integer.parseInt(board.getTokeni()[2].getNr())+" "+name);


                    }
                    else
                    if(board.getTokeni()[3].isAvailableToken())
                    {
                        System.out.println("token3:sunt pe tura lui "+name);
                        board.setnumberOfTokens(board.getnumberOfTokens() - 1);

                        board.getTokeni()[3].setAvailableToken(false);
                        numere.add(Integer.parseInt(board.getTokeni()[3].getNr()));

                        t++;
                        progresii[1][1]=2;
                        progresii[1][2]=1;
                        progresii[1][3]=2;



                        System.out.println("Number consumed: " + Integer.parseInt(board.getTokeni()[3].getNr())+" "+name);


                    }
                    else
                    if(board.getTokeni()[4].isAvailableToken())
                    {


                        System.out.println("token4:iar eu pe tura lui "+name);
                        board.setnumberOfTokens(board.getnumberOfTokens() - 1);

                        t++;
                        progresii[1][1]=2;
                        progresii[1][2]=2;
                        progresii[1][3]=2;



                        board.getTokeni()[4].setAvailableToken(false);
                        numere.add(Integer.parseInt(board.getTokeni()[4].getNr()));


                        System.out.println("Number consumed: " + Integer.parseInt(board.getTokeni()[4].getNr())+" "+name);


                    }

                }

                ////pasare turn

                board.setTurn(board.getTurn()%2+1);
            }

    }
}
