package sample;

import javafx.application.Platform;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Game extends Thread {
    private Game game;
    private Integer dimension;
    public Board
    board;
    public Double time=0.d;
    public Controller controller;



    public Game(Integer dimension,Controller controller) {
        this.dimension = dimension;
        this.controller=controller;
    }


    public void InitGame(Board board){


       // board.setnumberOfTokens(nrTokens);
       // board.getTokeni() =new Token[nrTokens+2];

        for(int i = 0; i<=board.getnumberOfTokens(); i++)
        {
            board.getTokeni()[i]=new Token(Integer.toString(i));

            board.getTokeni()[i].setAvailableToken(true);
            board.setAvailable(true);
        }

      //  board.getTokeni()[board.getnumberOfTokens()]=new Token("blank1");
       // board.getTokeni()[board.getnumberOfTokens()+1]=new Token("blank2");

    }



    @Override
    public void run() {

        try {
            sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("degeaba");
        ///JOC CU DIMENSIUNEA dimension-parametru transmis
        Integer nrTokens= dimension;
        Integer k=3;
         board=new Board(nrTokens);
        InitGame(board);

        board.setController(controller);

        ///INTRODUCERE PLAYERS
        //JOACA DE PE THREAD-URI DIFERITE


        ///Random game

        Runnable r1 = new RandomPlayer("Radius",board,7,1);
        Runnable r2 = new RandomPlayer("Cubitus",board,7,2);
        TimeKeeper timekeeper = new TimeKeeper("Timekeeper Smith",board);

        new Thread(r1).start();
        new Thread(r2).start();
        Thread t = new Thread(timekeeper);
        t.start();

        //abia dupa finalizarea lui timekeeper se vor face restul din parinte
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ///contorizre timp
        time= timekeeper.getTime();
        if(time>=24)
        {
            board.setMessage("Upss...Dar asta e jenant!\nIncearca sa repornesti jocul!\nSe mai intampla cand lasi totul la voia Fortunei!");
            Platform.runLater(()->{controller.winnerLabel.setText(board.getMessage()+"");});
        }

        //scriere pe label
        if(time!=0)
            Platform.runLater(()->{controller.timeLabel.setText(time+"");});

        if(!board.getWinner().equals("0"))
        {
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(()->{controller.winnerLabel.setText(board.getMessage()+"");});
        }


        System.out.println(  time+"");




        ////Smart game
/*
        Runnable r1 = new SmartPlayer("Temporal",board,k,1);
        Runnable r2 = new SmartPlayer("Extemporal",board,k,2);
        new Thread(r1).start();
        new Thread(r2).start();

*/

        ////Manual game
/*
        Runnable r1 = new ManualPlayer("Little Finger",board,k,1);
        Runnable r2 = new ManualPlayer("Big Finger",board,k,2);
        new Thread(r1).start();
        new Thread(r2).start();

*/


    }

    public Double getTime() {
        return time;
    }
}
