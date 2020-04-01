package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ManualPlayer extends Player {

    private Board board;
    private List<Integer> numere=new ArrayList<>();
    private Integer kapa;
    private Integer myTurn;

    public ManualPlayer(String name,Board board,Integer kapa,Integer turn )
    {
        this.name = name;
        this.board = board;
        this.kapa=kapa;
        this.myTurn=turn;
    }
/////trb implementat un turn in board


    @Override
    public void run() {
        System.out.println("AM INTRAT AICISA FRAIERE "+ name);

        ///se verifica daca mai sunt piese pe tabla
        ///(trebuie citit din buffer)
        ///si apoi eliminat din buffer

        //daca mai sunt se alege un nr random
        //si playerul ia piesa de pe tabla


////////////////////////////////////////////////////////////////////////
      //citire de la tastatura pana cand unul e viabil si daca e viabil se scoate de pe plansa


        Integer nr;
        while(board.getMaximul()<kapa && board.getnumberOfTokens()>0)
            if(board.getTurn()==myTurn) {

                nr = board.getnumberOfTokens();


                System.out.println("aici reusim "+name);


                Integer num=0;

                BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
                try {
                    num = Integer.parseInt(stdin.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }


                ///am reusit extragerea unui nr din buffer si setarea lui ca INVALID
                ///plus
                ///adaugare nr in setul de nr al player-ului

                for(int i=1;i<=board.getnumberOfTokens();i++)
                {
                    if(board.getTokeni()[i].getNr().equals(num))
                        if(board.getTokeni()[i].isAvailableToken())
                        {

                            board.setnumberOfTokens(board.getnumberOfTokens() - 1);

                            board.getTokeni()[num].setAvailableToken(false);
                            numere.add(num);
                            System.out.println("Number consumed: " + num);
                            break;



                        }
                        else {

                            try {
                                num = Integer.parseInt(stdin.readLine());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            i=1;
                        }

                }

//////////////////////////////////////////////////////////////////////////////////////////


                ///sortare lista

                numere.sort(Integer::compareTo);


                ///verificarea progresiei maxime existente

                int dif = 0, lg, max = 0, poz=0, pozdif=0;
                int player1, player2;


                ///ALGORITM VERIFICARE PROGRESIE
                //se iau 2 nr din numere
                //se calculeaza diferenta lor si se presupune existenta unei progresii pornind de la acei termeni
                //se cauta in numere fiecare termen care ar tb sa apartina progresiei
                //daca nu exista vreun termen, se compara cu maximul si se iese
                //continuam cautarea progresiei

                for (int i = 0; i <numere.size() ; i++)
                {
                    for (int j = i+1; j <numere.size(); j++)
                    {

                        dif = numere.get(j) - numere.get(i);

                        //if(j!=i)
                        lg = 2;
                        //else lg=1;

                        if (lg > max) {
                            max = lg;
                            poz = i;
                            pozdif = dif;
                        }



                        for (int k = numere.get(j) + dif; k <= Collections.max(numere); k += dif)

                           if(numere.contains(k))
                            {
                                lg++;
                                if (lg > max)
                                {
                                    max = lg;
                                    poz = i;
                                    pozdif = dif;

                                }

                            }
                            else
                            {
                                break;
                            }
                    }


                }
                if(max>board.getMaximul())
                    board.setMaximul(max);


                //jocul se termina daca progresia max este de lg kapa
                //sau daca tabla este goala
                if(max==kapa)
                {
                    if (board.getPlayer1Score() == 0)
                        board.setPlayer1Score(max);
                    else
                        board.setPlayer2Score(max);

                    player1 = board.getPlayer1Score();
                    player2 = board.getPlayer2Score();



                    if (max == kapa) {



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

                board.setTurn(board.getTurn()%2+1);
            }


    }
}
