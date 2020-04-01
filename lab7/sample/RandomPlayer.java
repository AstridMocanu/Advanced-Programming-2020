package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.sql.SQLOutput;
import java.util.*;

import static java.lang.Thread.sleep;

public class RandomPlayer extends Player {

    private Board board;
    private List<Integer> numere=new ArrayList<>();
    private Integer kapa;
    private Integer myTurn;
    private Integer maxSupreme=0;
    private Integer pozSupreme=0;
    private Integer difSupreme=0;
    private static Integer player1=0, player2=0;
    private static Double[][] imagini;


      public RandomPlayer(String name,Board board,Integer kapa,Integer turn )
    {
        this.name = name;
        this.board = board;
        this.kapa=kapa;
        this.myTurn=turn;
        imagini=board.getController().imagini;
        }
/////trb implementat un turn in board


    @Override
    public void run() {

        System.out.println("AM INTRAT AICISA FRAIERE "+ name);

        //jucatorul de baza - implementat si in interfata
        ///se verifica daca mai sunt piese pe tabla
                ///(trebuie citit din buffer)
                ///si apoi eliminat din buffer

        //daca mai sunt tokeni se alege un nr random
        //si playerul ia piesa de pe tabla





        Random rand = new Random();
        int random = rand.nextInt(1000);


        //board.setAvailable(true);
        Integer nr;

        //cat timp nu a castigat nimeni sau tabla nu e goala
        while(board.getMaximul()<kapa && board.getnumberOfTokens()>0 && board.getWinner().equals("0"))
        {


            //daca e randul meu
            if(board.getTurn()==myTurn&&board.getWinner().equals("0")) {

                System.out.println(board.getWinner());
                nr = board.getnumberOfTokens();


                System.out.println("Este randul lui:"+name);


                Integer num;
                num = random % nr+1;
                Integer value;


                ///extragerea unui nr din buffer si setarea lui ca INVALID
                ///plus
                ///adaugare nr in setul de nr al player-ului
                ///stergerea lor de pe tabla

                while (!board.getTokeni()[num].isAvailableToken()) {
                    random = rand.nextInt(1000);
                    num = random % (nr-1)+1;


                }


                if(board.getTokeni()[num].isAvailableToken()) {


                    value = num;

                    board.setnumberOfTokens(board.getnumberOfTokens() - 1);



                    board.getTokeni()[num].setAvailableToken(false);
                    numere.add(num);
                    board.getController().setTokenScos(num);

                    GraphicsContext graphics_context = board.getController().canvas.getGraphicsContext2D();
                    //graphics_context.setFill(new Color(255.d/255,244.d/255,223.d/255,1));
                    graphics_context.setFill(new Color(Math.random(),Math.random(),Math.random(),1));
                    graphics_context.fillRect(imagini[num][3], imagini[num][4],imagini[num][1], imagini[num][2]);

                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    System.out.println("Number consumed: " + value);

                }


                ///sortare lista

                numere.sort(Integer::compareTo);


                ///verificarea progresiei maxime existente

                int dif = 0, lg, max = 0, poz=0, pozdif=0;



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
                //}

                if(max>board.getMaximul()) {
                    board.setMaximul(max);
                }

                if(max>maxSupreme){
                    maxSupreme=max;
                    pozSupreme=poz;
                    difSupreme=pozdif;
                }

                System.out.println(name+ " a obtinut o progresie cu lungimea:"+maxSupreme+" incepand de la nr: "+numere.get(pozSupreme)+" ccu dif de: "+difSupreme);
                System.out.println("pana acum el are numerele: "+numere);

                //jocul se termina daca progresia max este de lg kapa
                //sau daca tabla este goala
                //sau daca e depasit timpul

                if(myTurn==1) {
                   board.setPlayer1Score(max);
                    player1 = board.getPlayer1Score();
                }
                    else
                { board.setPlayer2Score(max);
                  player2 = board.getPlayer2Score();

                }

                if (max >= kapa) {

                    if (max % 3 == 0)
                    {
                        System.out.println("Now we have a WINNER!!\nWhat's your name,son?\n" + name);
                        board.setWinner(name);

                        board.setMessage("Now we have a WINNER!!\nWhat's your name,son?\n" + name);
                    }

                    if (max % 3 == 1)
                    {

                        System.out.println("Winner, winner...of the Board!\nWho's the best player of them all?\n" + name);
                        board.setWinner(name);
                        board.setMessage("Winner, winner...of the Board!\nWho's the best player of them all?\n" + name);

                    }

                    if (max % 3 == 2)
                    {
                        System.out.println("Good judgment comes from bad experience, and a lot of that comes from bad judgment\nWell done " + name);
                        board.setWinner(name);
                        board.setMessage("Good judgment comes from bad experience, and a lot of that comes from bad judgment\nWell done " + name);
                    }

                    board.setMessage(board.getMessage()+"\nAm castigat cu progresia: ");
                    for(int i=numere.get(poz), contor=1;contor<=kapa;i+=pozdif,contor++)
                    {
                        System.out.println(i+ " ");
                        board.setMessage(board.getMessage()+i+" ");
                    }

                    System.out.println();
                    System.out.println(numere);

                }
                else
                    if(board.getnumberOfTokens()==0)
                    {
                    if(player1!=0 &&player2!=0)
                    {
                        if(player1>player2)///a castigat player1
                        {
                            //if (max == player1)//daca tu esti player1
                            if(myTurn==1)
                            {
                                if (max % 3 == 1)
                                {
                                    System.out.println("Now we have a WINNER!!\nWhat's your name,son?\n" + name);
                                    board.setWinner(name);
                                    board.setMessage("Now we have a WINNER!!\nWhat's your name,son?\n" + name);

                                }


                                if (max % 3 == 0)
                                {
                                    System.out.println("Winner, winner...of the Board!\nWho's the best player of them all?\n" + name);
                                    board.setWinner(name);
                                    board.setMessage("Winner, winner...of the Board!\nWho's the best player of them all?\n" + name);

                                }

                                if (max % 3 == 2)
                                {
                                    System.out.println("Good judgment comes from bad experience, and a lot of that comes from bad judgment\nWell done " + name);
                                    board.setWinner(name);
                                    board.setMessage("Good judgment comes from bad experience, and a lot of that comes from bad judgment\nWell done " + name);
                                }

                                board.setMessage(board.getMessage()+"\nAm castigat cu progresia: ");
                                for(int i=numere.get(pozSupreme), contor=1;contor<=maxSupreme;i+=difSupreme,contor++)
                                {
                                    System.out.println(i+ " ");
                                    board.setMessage(board.getMessage()+i+" ");
                                }

                                System.out.println();
                                System.out.println(numere);


                            }
                            else //esti player2
                                System.out.println("Our loser is "+name+" #SadSound#");

                        }
                        else
                        {

                            if(player2>player1)//a castigat player2
                            {
                               // if (max == player2)//daca tu esti player2
                                if(myTurn==2)
                                {
                                    if (max % 3 == 2)
                                    {
                                        System.out.println("Now we have a WINNER!!\nWhat's your name,son?\n" + name);
                                        board.setWinner(name);
                                        board.setMessage("Now we have a WINNER!!\nWhat's your name,son?\n" + name);

                                    }


                                    if (max % 3 == 1)
                                    {
                                        System.out.println("Winner, winner...of the Board!\nWho's the best player of them all?\n" + name);
                                        board.setWinner(name);
                                        board.setMessage("Winner, winner...of the Board!\nWho's the best player of them all?\n" + name);

                                    }

                                    if (max % 3 == 0)
                                    {
                                        System.out.println("Good judgment comes from bad experience, and a lot of that comes from bad judgment\nWell done " + name);
                                        board.setWinner(name);
                                        board.setMessage("Good judgment comes from bad experience, and a lot of that comes from bad judgment\nWell done " + name);
                                    }

                                    board.setMessage(board.getMessage()+"\nAm castigat cu progresia: ");
                                    for(int i=numere.get(pozSupreme), contor=1;contor<=maxSupreme;i+=difSupreme,contor++) {
                                        System.out.println(i + " ");
                                        board.setMessage(board.getMessage()+i+" ");
                                    }

                                    System.out.println();
                                    System.out.println(numere);
                                }
                                else //esti player1
                                    System.out.println("Our loser is "+name+" #SadSound#");
                            }
                            else
                            {
                                System.out.println("If at first you don’t succeed, skydiving is not for you.");
                                System.out.println("Egalitate...de data asta...");
                            }
                        }

                    }


                    else System.out.println("eroare de sistem..");




                }



                System.out.println();
                board.setTurn(board.getTurn()%2+1);



            }
            else
                if(board.getTurn()!=myTurn) {
                   /* try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/

                    if(!board.getWinner().equals("0"))
                        System.out.println("Am pierdut!Cat de trist!"+name);
                }

        }

        System.out.println(board.getWinner() + " : " +name);



    }
}
