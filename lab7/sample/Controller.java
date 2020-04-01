package sample;

import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.StrictMath.sqrt;
import static java.lang.Thread.sleep;
import static javafx.scene.paint.Color.*;
import static javafx.scene.text.TextAlignment.CENTER;


public class Controller implements Initializable {

    @FXML
    Canvas canvas;
    public Button exitButton;
     public Button startButton;
     public Double[][] imagini=new Double[1000][10];
    Game game;
    @FXML
    Label timeLabel;
    @FXML
    Label winnerLabel;
    private Integer tokenScos=0;


    public void exitButton(javafx.event.ActionEvent actionEvent) {

        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
        
        ///am descoperit mai tarziu (dupa ora 00:00) ca puteam inchide procesul folosind .setRunning(false)
        //game.setRunning(false)

        System.out.println("inchis!");
    }


    public void startButton(javafx.event.ActionEvent actionEvent)

    {
        int n=63;
        InitializareTokeni(n);

        game=new Game(n,this);
        game.start();

        System.out.println("am apasat start");
    }

    @FXML
    public void InitializareTokeni(int dimension){
        GraphicsContext graphics_context = canvas.getGraphicsContext2D();


        graphics_context.setFill(new Color(255.d/255,244.d/255,223.d/255,1));
        graphics_context.fillRect(1,1,800,800);


        String path = System.getProperty("user.dir");

       double h=canvas.getHeight();
        double w=canvas.getWidth();

        h=h/sqrt(dimension+30);
        w=w/sqrt(dimension+30);
        int contor=0;
        for(int i=1;i<=9;i++)
        for(int j=1;j<=7;j++) {//"https://clipground.com/images/spring-blossoms-clipart-1.png"
            graphics_context.drawImage(new Image("file:"+path+"/src/sample/sakura1.png", h, w, true, true), i*h, j*w);//desenare tokeni

            ++contor;
            imagini[contor][1]=h;
            imagini[contor][2]=w;
            imagini[contor][3]=(double)(i)*h;
            imagini[contor][4]=(double)j*w;

            graphics_context.setStroke(DEEPPINK);
            graphics_context.strokeText(contor+"" , i*h+15, j*w+15);
        }







    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





    }

    public Integer getTokenScos() {
        return tokenScos;
    }

    public void setTokenScos(Integer tokenScos) {
        this.tokenScos = tokenScos;
    }
}



