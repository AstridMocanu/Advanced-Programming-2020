package sample;

import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;

public class Board {

    private Integer numberOfTokens;
    private boolean available = true;
    private Boolean[] tokensAvailability;
    private Token[] tokeni;
    private Integer player1Score=0;
    private Integer player2Score=0;
    private Integer turn=1;
    private Integer maximul=0;
    private String winner=new String("0");
    private Integer poz=0;
    private Integer dif=0;
    public String message="0";
    private Controller controller;

/**joaca rol de BUFFER*/

    public Board(Integer numberOfTokens) {
        this.numberOfTokens = numberOfTokens;
        tokeni=new Token[numberOfTokens+1];
        tokensAvailability=new Boolean[numberOfTokens+1];
    }


    public synchronized Integer getnumberOfTokens() {

        /*
        if(turn==1){
        while(turn==1){

            try{
                //wait();
            }catch (InterruptedException e) { e.printStackTrace(); }
        }

        turn=2;
        notifyAll();
      }
        else
        if(turn==2){
            while(turn==2){

                try{
                   // wait();
                }catch (InterruptedException e) { e.printStackTrace(); }
            }

            turn=1;
            notifyAll();
            }

         */

        /*

        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        available = false;
        notifyAll();*/
        return numberOfTokens;
    }

    public synchronized void setnumberOfTokens(Integer number) {
/*
        if(turn==1) {
            while (turn == 1) {

                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            turn = 2;
            numberOfTokens = number;
            notifyAll();
        }
        else
        if(turn==2){
            while(turn==2){

                try{
                    wait();
                }catch (InterruptedException e) { e.printStackTrace(); }
            }

            turn=1;
            */
            numberOfTokens = number;
          //  notifyAll();
            //}







        /*
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        numberOfTokens = number; available = true;
        notifyAll();

         */
    }


    public synchronized Boolean[] getTokensAvailability() {

        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }


        available = false;
        notifyAll();

        return tokensAvailability;
    }


    public synchronized void setTokensAvailability(Boolean[] tokensAvailability,boolean availability) {

        while (available) {
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }


        this.tokensAvailability = tokensAvailability;

        available = true;
        notifyAll();
    }

    public Token[] getTokeni() {
        return tokeni;
    }

    public void setTokeni(Token[] tokeni) {
        this.tokeni = tokeni;
    }

    public Integer getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(Integer player1Score) {
        this.player1Score = player1Score;
    }

    public Integer getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(Integer player2Score) {
        this.player2Score = player2Score;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public synchronized Integer getTurn() {
        /*while (!available) {
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }


        available = false;
        notifyAll();

         */

        return turn;
    }

    public synchronized void setTurn(Integer turn) {
      /*  while (available) {
            try {
                wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
*/
        this.turn = turn;
        //available = true;
        //notifyAll();
    }

    public Integer getMaximul() {
        return maximul;
    }

    public void setMaximul(Integer maximul) {
        this.maximul = maximul;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Integer getPoz() {
        return poz;
    }

    public void setPoz(Integer poz) {
        this.poz = poz;
    }

    public Integer getDif() {
        return dif;
    }

    public void setDif(Integer dif) {
        this.dif = dif;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


    /*

    public void setAvailabilityFor (Token token,boolean availability)throws NonExistentTokenException{

        boolean ok;

            try {
                Double.parseDouble(token.getNr());
                ok=true;
            } catch(NumberFormatException e)
            {
                ok=false;
            }



try{
                if (ok)
                tokensAvailability[Integer.parseInt(token.getNr())] = availability;
            else if (token.getNr().equals("blank1"))
                tokensAvailability[Integer.parseInt(token.getNr()) + 1] = availability;
            else if (token.getNr().equals("blank2"))
                tokensAvailability[Integer.parseInt(token.getNr()) + 2] = availability;
            else
                throw new NonExistentTokenException(new Exception());
        }catch( NonExistentTokenException e){e.printStackTRace();};


    }

     */


}
