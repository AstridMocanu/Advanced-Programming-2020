package lab11.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private String content;
    private String date;
    private String result;
    private String[] players;




    /*
    ///1 board
    ///2 playeri
    ////constructor cu posibilitate de alegere-dimensiune,ordine,culoare(swap2)
    ///afisare mesaj player turn
    ///ales random cine e primul
    ///pe parcurs posibilitate de alegere daca se foloseste sau nu swap2

    private Player player1;
    private Player player2;
    //private Board board;
    private Integer dimensiune;
    private Integer turn;
    private Integer moveLinie;
    private Integer moveColoana;
    private Integer id;
    static private Integer idMaxim=0;
    static private List<Game> listaGames= new ArrayList<Game>();
    private Integer pozl=-2,pozc=-2;
    private Integer winner=0;

    ////CONSTRUIESC JOCUL
    public Game(Integer dimensiune) {
        this.player1 = new Player(1,"banana");
        // this.player2 = new Player(2);

        this.dimensiune = dimensiune;
    //    this.board = new Board(this.dimensiune);
        this.id= idMaxim++;
        listaGames.add(this);


        Random random=new Random();
        // this.turn =random.nextInt()%2+1 ;
        this.turn =2 ;

    }

    ////adaug player la joc existent
    public Boolean addPlayer(){


        if(this.player1==null)
        {
            return false;}
        else
        if(this.player2==null)
        {
            player2=new Player(2,"capsunica");
            return true;
        }

        return false;

    }

    ///INREGISTREZ MUTAREA FACUTA
    ///TB OBS PE TURA CUI ESTE SI TRECUTA LA MUTARILE LUI
    ///fara a lua in calcul cazul swap2

    public Boolean mutare(Integer linie,Integer coloana,Integer turn){
        this.moveLinie=linie;
        this.moveColoana=coloana;

        if(turn==1)
        {
            if(board.verificaValiditateMutare(moveLinie,moveColoana)){

                System.out.println("pe drumul bun 1");

                board.adaugaMutare(moveLinie,moveColoana);
                boolean captat=player1.adaugaMutare(moveLinie,moveColoana);
                if(captat)winner=1;

                return true;
            }

            return false;
        }
        else
        if(turn==2)
        {
            if(board.verificaValiditateMutare(moveLinie,moveColoana)){

                System.out.println("pe drumul bun 2");

                board.adaugaMutare(moveLinie,moveColoana);
                boolean captat=player2.adaugaMutare(moveLinie,moveColoana);
                if(captat)winner=2;
                return true;
            }
            return false;
        }


        return false;
    }


    static public Game getGame(int nr){

        if(idMaxim<nr)return null;

        return listaGames.get(nr-1);
    }




    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Integer getDimensiune() {
        return dimensiune;
    }

    public void setDimensiune(Integer dimensiune) {
        this.dimensiune = dimensiune;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public Integer getPozl() {
        return pozl;
    }

    public void setPozl(Integer pozl) {
        this.pozl = pozl;
    }

    public Integer getPozc() {
        return pozc;
    }

    public void setPozc(Integer pozc) {
        this.pozc = pozc;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }*/
}
