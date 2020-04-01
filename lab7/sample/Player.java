package sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Player implements Runnable {
    public String name;




    /*
    @Override
    public void run() {

        int count = 100_000_000;
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter("hello.txt"))) {
            for (int i = 0; i < count; i++) {
                out.write("Hello World!\n");
            }
        } catch (IOException e) {
            System.err.println("Oops..." + e);
        }


    }
*/

}
