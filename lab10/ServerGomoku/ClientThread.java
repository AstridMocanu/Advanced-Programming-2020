import com.jcraft.jsch.SftpException;

import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;
import java.time.LocalDate;

class ClientThread extends Thread {

    private static int maxId = 1;

    private int id;
    private Socket socket = null ;
    public ClientThread (Socket socket) { this.socket = socket ; id = maxId++; }

    private Game game;
    private Integer nrTurn=1;
    private Integer pozl=0,pozc=0;
    PrintStream fout;
    String filename;

    void afisareFisier(String text){
        try {
            fout=new PrintStream( new FileOutputStream("joc"+game.getId()+".sgf",true));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fout.println(text);
        fout.flush();
        fout.close();

    }


    public void run () {



        try
        {

            ///in request avem comanda de la client citita
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String request = in.readLine();

            ///in raspuns vom scrie un anumit raspuns pe care il asteapta
            //in cazul de fata raspunsul va putea fi o pozitie
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String raspuns ;
            raspuns="0";


            if(request.equals("create game")){

                raspuns="joc creat";
                ///by default dim=15
                game=new Game(15);
                nrTurn=1;

                try {
                    fout=new PrintStream( new FileOutputStream("joc"+game.getId()+".sgf"));

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                filename="joc"+game.getId()+".sgf";

                LocalDate date = LocalDate.now();
                fout.println("DT "+date);
                fout.println("PB Blacky");
                fout.println("PW Whitey");
                fout.println("FF Version:Astrid");
                fout.println("EV Marea Tema la Java Saptamanala");
                fout.flush();

                fout.flush();

                fout.close();


                out.println(raspuns);
                debug(raspuns);
                out.flush();

               // out.println("acum este tura ta 2");
               // out.flush();
            }
            else  if(request.contains("join game"))
            {


                    int nr=0,ok=1;
                    request=request.replace("join game ","");



                    try {
                        nr=Integer.parseInt(request);
                    } catch (NumberFormatException nfe) {
                        raspuns="nu ati introdus un numar valid. incercati din nou.";
                        ok=0;
                    }

                    if(ok==1)
                    {
                        debug("se incearca accesarea jocului");
                        if(Game.getGame(nr)!=null)
                            if(Game.getGame(nr).addPlayer())
                            {raspuns="v-ati alaturat jocului";
                            id=nr;
                            game=Game.getGame((nr));

                                try {
                                    fout=new PrintStream( new FileOutputStream("joc"+game.getId()+".sgf",true));

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }






                            }
                            else
                                raspuns="nu exista joc disponibil. creati unul nou ";
                        else
                            raspuns="nu exista jocul cu nr de ordine introdus. incercati din nou";
                    }



                out.println(raspuns);
                debug(raspuns);
                out.flush();

                Game.getGame(nr).setTurn(1);
                nrTurn=2;

               // out.println("acum este tura ta 1");
               // out.flush();

            }
         //   request = in.readLine();
            String r2 = "0";

            while(!request.equals("stop")&&game.getWinner()==0){

                debug(request);
                System.out.flush();

                raspuns="0";

                debug("turn of "+nrTurn);
                debug("game = " + game.toString() + " turn = " + game.getTurn());

                while(!game.getTurn().equals(nrTurn)){
                    //if(game.getPozl()==-2)if(game.getPozl()==-2)if(nrTurn==2)break;
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
                debug("am iesit din while: game = " + game.toString() + " turn = " + game.getTurn());

                if(game.getWinner()==0)
                    r2="poz: "+game.getPozl()+" "+game.getPozc();
                else {
                    r2="ati pierdut";debug("r2 = " + r2);
                    afisareFisier("WIN: "+game.getWinner());
                    //fout.flush();
                    UploadController uc=new UploadController();
                    uc.urcare(filename);
                    out.println(r2);
                    out.flush();
                    break;
                }
                debug("r2 = " + r2);
                out.println(r2);
                out.flush();

                request=in.readLine();



                /////creare joc
             /*   if(request.equals("create game"))
                {
                    raspuns="joc creat";
                    ///by default dim=15
                    game=new Game(15);
                    nrGame=game.getTurn();

                }

                ////adugare player
                if(request.contains("join game"))
                {
                    int nr=0,ok=1;
                    request=request.replace("join game","");
                    request=request.replace(" ","");


                    try {
                        nr=Integer.parseInt(request);
                    } catch (NumberFormatException nfe) {
                        raspuns="nu ati introdus un numar valid. incercati din nou.";
                        ok=0;
                    }

                    if(ok==1)
                        if(game.getGame(nr)!=null)
                            if(game.getGame(nr).addPlayer())
                                raspuns="v-ati alaturat jocului";
                            else
                                raspuns="nu exista joc disponibil. creati unul nou ";
                        else
                            raspuns="nu exista jocul cu nr de ordine introdus. incercati din nou";
                }
*/
                ///fa mutare
                if(request.contains("submit move"))
                {
                    Integer linie=0,coloana=0,nr=0;
                    String[] arr;
                    int ok=1;


                    ///parsare mesaj
                    request=request.replace("submit move ","");

                    arr=request.split(" ");

                    ///extragere nr joc
                    try {
                       /// System.out.println(arr[0]+";");
                        nr=Integer.parseInt(arr[0]);
                        id=nr;



                    } catch (NumberFormatException nfe) {
                        raspuns="nu ati introdus id joc ca un numar valid. incercati din nou.";
                        ok=0;
                    }

                    ///extragere linie
                    try {
                     ////   System.out.println(arr[1]+";");
                        linie=Integer.parseInt(arr[1]);
                    } catch (NumberFormatException nfe) {
                        raspuns="nu ati introdus linia ca un nr valid. incercati din nou.";
                        ok=0;
                    }

                    //extragere coloana
                    try {
                       //// System.out.println(arr[2]+";");
                        coloana=Integer.parseInt(arr[2]);
                    } catch (NumberFormatException nfe) {
                        raspuns="nu ati introdus coloana ca un numar valid. incercati din nou.";
                        ok=0;
                    }

                    if(ok==1)
                        if(Game.getGame(nr)!=null)
                        {
                            debug(Game.getGame(nr).getTurn()+"asta");
                            if(Game.getGame(nr).mutare(linie,coloana,Game.getGame(nr).getTurn()))
                            {
                                raspuns="mutare inregistrata";

                                if(game.getWinner()!=0)
                                    raspuns="ati castigat";

                                Game.getGame(nr).setPozl(linie);
                                Game.getGame(nr).setPozc(coloana);


                                   if(Game.getGame(nr).getTurn()==1)
                                       afisareFisier("B:"+linie+" "+coloana);
                                   else
                                   if(Game.getGame(nr).getTurn()==2)
                                       afisareFisier("W:"+linie+" "+coloana);

                                   fout.flush();


                                Game.getGame(nr).setTurn((Game.getGame(nr).getTurn())%2+1);
                            }
                            //invalid
                            else
                            {
                                raspuns="mutare invalida. incercati din nou.";

                            }
                        }
                        else
                            raspuns="nu exista jocul cu nr de ordine introdus. incercati din nou";

                }


                if(request.equals("exit")){

                    raspuns="un player a iesit din joc";
                    debug("in exit sunt");
                    break;
                    ///ar fi bine ca celalat sa fie scos
                }

                if(request.equals("swap2")){
                    //primul jucator face 3 mutari,2 negru si 1 alb
                    //al doilea are 3 variante: -joaca cu negru
                    //                          -joaca cu alb si mai pune o piesa
                    //                          -nu decide cu ce joaca,mai pune 2 piese si il lasa pe 1 sa decida el.
                }

                out.println(raspuns);
                debug(raspuns);
                out.flush();

                request=in.readLine();
                if(!request.equals("ok")){
                    debug("EROARE - NU AM PRIMIT OK-UL");
                }

              //  out.println(r2);



             //   request = in.readLine();
            }
                if(request.equals("stop"))
                    raspuns="Server stopped";

            out.println(raspuns);
            debug(raspuns);
            out.flush();
        } catch (IOException | SftpException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();

            } catch (IOException e) { System.err.println (e); }
        }
    }

    private void debug(String text){
        System.out.println("[thread " + id + " " + nrTurn + "]: " + text);
    }
}