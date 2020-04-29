package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {

    public static void main (String[] args) throws IOException {
        Integer myTurn=0;
        Integer mutarel=-1;
        Integer mutarec=-1;

        String serverAddress = "127.0.0.1"; // The server's IP address
        int PORT = 8120; // The server's port
        try
                (
                        Socket socket = new Socket(serverAddress, PORT);
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {

            ///initial citesc requestul de la tastatura
            ///dupa testare -> tb preluate din controller
            Scanner console = new Scanner(System.in);

            String request = console.nextLine();
            String response="0";
            int ok=1,nr = 0;

            ///initializare turn in fct de prima comanda
            if(request.equals("create game"))
            {myTurn=1;
                System.out.println("tura ta este:"+myTurn);
                out.println(request);
                out.flush();
                response = in.readLine ();
                System.out.println(response);
                System.out.flush();
            }
            else if(request.contains("join game"))
            {myTurn=2;
                System.out.println("tura ta este:"+myTurn);
                out.println(request);
                out.flush();
                response = in.readLine ();
                System.out.println(response);
                System.out.flush();
            }

            //request = console.nextLine();
            String[] arr;
            Integer nr1=-1,nr2=-1;
            response = in.readLine();
            System.out.println(response);
            while(!request.equals("exit")){

                ok=1;
                System.out.println("am intrat in while "+request);
                /////clientul nu scrie nimic in buffer daca nu e tura lui
                ///asteapta mesajul:ACUM ESTE TURA TA
                // + ultima mutare a adversarului
                if(response.contains("poz: ")){
                    System.out.println(response);
                    System.out.flush();


                    String rsp=response.replace("poz: ","");

                    arr=rsp.split(" ");

                    try {
                        nr1=Integer.parseInt(arr[0]);
                    } catch (NumberFormatException nfe) {
                        ok=0;
                    }
                    try {
                        nr2=Integer.parseInt(arr[1]);
                    } catch (NumberFormatException nfe) {
                        ok=0;
                    }

                    if(ok==1)
                        if(nr1!=mutarel||nr2!=mutarec){///mutarea primita este a adversarului

                            System.out.println("este tura mea");
                            request = console.nextLine();
                            out.println(request);
                            out.flush();

                            response = in.readLine ();
                            System.out.println(response);

                            if(request.contains("submit move")&&response.equals("mutare inregistrata")){
                                request=request.replace("submit move ","");
                                arr=request.split(" ");

                                try {
                                    /// System.out.println(arr[0]+";");
                                    nr1=Integer.parseInt(arr[0]);



                                } catch (NumberFormatException nfe) {
                                    System.out.println("nu ati introdus id joc ca un numar valid. incercati din nou.");
                                    ok=0;
                                }


                                try {
                                    /// System.out.println(arr[0]+";");
                                    nr2=Integer.parseInt(arr[0]);



                                } catch (NumberFormatException nfe) {
                                    System.out.println("nu ati introdus id joc ca un numar valid. incercati din nou.");
                                    ok=0;
                                }

                                if(ok==1){
                                    mutarel=nr1;
                                    mutarec=nr2;
                                }

                            }
                            if(request.equals("stop"))
                                break;


                        out.println("ok");
                        out.flush();
                        }
                }
                if(response.contains("pierd")){
                    System.out.println(response);
                    break;
                }

                System.out.println("Va rog asteptati");
                response = in.readLine ();
                if(response.contains("castig")){
                    System.out.println(response);
                    break;
                }
                System.out.println("din in:"+response);


            }
            //request = console.nextLine();


            // if(request.equals("stop"))
            //out.println("stop");
            //  out.println(request);

        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}