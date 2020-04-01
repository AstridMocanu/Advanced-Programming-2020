package sample;

import static java.lang.Thread.sleep;

public class TimeKeeper implements Runnable {
    private String name;
    private String win;
    private Board board;
    private Double time=0.d;

    public TimeKeeper(String name,Board board) {
        this.name=name;
        this.win=board.getWinner();
        this.board=board;

            }

    @Override
    public void run() {
        /*while(board.getWinner().equals("0")) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(board.getWinner());
        System.out.println(System.nanoTime());*/
        boolean ok=true;
        long nr=System.nanoTime();
        while(ok){

            try {
                sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!board.getWinner().equals("0")){
                try {
                    sleep(65);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ok=false;
            }
            ////opreste jocul daca dureaza nenormal de mult
            time=(double)(System.nanoTime()-nr)/1_000_000_000;
            if(time>=25)ok=false;

        }

        System.out.println("Timpul jocului: " +  (double)(System.nanoTime()-nr)/1_000_000_000 +" secunde");
        time=(double)(System.nanoTime()-nr)/1_000_000_000;
    }

    public Double getTime() {
        return time;
    }
}
