package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.DragEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.StrictMath.*;

public class Controller implements Initializable {
    public Button exitButton;
    public Button saveButton;
    public Button loadButton;
    public Button resetButton;
    public Canvas canvas;
    public TextField size;
    public TextField sides;
    public ColorPicker colorPicker;
    public ComboBox<String> comboBox;



    public void ExitButton(javafx.event.ActionEvent actionEvent) {

        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
         System.out.println("inchis!");
    }

    public void LoadButton(javafx.event.ActionEvent actionEvent){

        /**Creaza un obiect de tip FileChooser
            Deschidem fereastra de EXPLORARE
            Alegem o imagine si o vom stoca intr-un obiect de tip Image folosindu-i PATH-UL
            si o afisam pe canvas

         * */


        FileChooser fileChooser = new FileChooser();

        Stage primaryStage = (Stage) loadButton.getScene().getWindow();
        File file = fileChooser.showOpenDialog(primaryStage);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        if(file != null) {
            System.out.println("file:" + file.getPath());
            Image image = new Image("file:" + file.getPath());
            graphicsContext.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight());
        }
    }

    public void SaveButton(javafx.event.ActionEvent actionEvent){

        /**Creaza un obiect de tip FileChooser
         Deschidem fereastra de EXPLORARE
         * Cream un obiect de tip WritableImage de dimensiunea cavasului
         * Punem in el un snapshot la canvas
         * Image e obiect specific JavaFX, deci il convertim la un alt tip de obiect compatibil cu primitivele de sistem
         * Stocam imaginea obtinuta cu formatul png
         */

        FileChooser fileChooser = new FileChooser();

        Stage primaryStage = (Stage) saveButton.getScene().getWindow();
        File file = fileChooser.showSaveDialog(primaryStage);

        if(file != null){
            try {
                WritableImage writableImage = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight());
                canvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }


    }}
    public void ResetButton(javafx.event.ActionEvent actionEvent){

        GraphicsContext graphics_context = canvas.getGraphicsContext2D();

        graphics_context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());



    }

    public void CanvasOnMouseClick(javafx.scene.input.MouseEvent event)throws StringIntegerException{



         double x,y;
        boolean ok=true;


         //////Extragere coordonate mouse

        x=event.getX();
         y=event.getY();

        ///Validare date input
        try {
            Double num = Double.parseDouble(size.getText());
        } catch (NumberFormatException e) {
            System.out.println(e);
            System.out.println("Introduceti un nr intreg va rugam");
            ok = false;
        }

        try {
            Double num = Double.parseDouble(sides.getText());
        } catch (NumberFormatException e) {
            System.out.println(e);
            System.out.println("Introduceti un nr intreg va rugam");
            ok = false;
        }

        if(ok)
         try {
             //validare date imput..pt posibilitatea creearii unei imagini
             double num = Double.parseDouble(sides.getText());
             if(num!=(int)num)throw new StringIntegerException(new Exception());
             num = Double.parseDouble(size.getText());
             if(num!=(int)num)throw new StringIntegerException(new Exception());

            ///desenare forme...orice fel de poligon regulat si cercuri si ovale

             if(comboBox.getValue().equals("OVAL"))
                 DrawOval(Integer.parseInt(size.getText()),x,y);
                 else
             if(comboBox.getValue().equals("REGULAR POLYGON"))
             DrawPolygon(Integer.parseInt(size.getText()), Integer.parseInt(sides.getText()), x, y);
         }
         catch(StringIntegerException|MMicK3Exception e){
             System.out.println(e);
         }


    }

    private void DrawOval(Integer size,double cx,double cy) {
        GraphicsContext graphics_context = canvas.getGraphicsContext2D();
        graphics_context.setFill(colorPicker.getValue());

        graphics_context.fillOval(cx,cy,size,size*2);

    }


    public void DrawPolygon(Integer size,Integer sides,double cx,double cy)throws MMicK3Exception{
         if (sides<3) throw new MMicK3Exception(new Exception());


        GraphicsContext graphics_context = canvas.getGraphicsContext2D();
        graphics_context.setFill(colorPicker.getValue());


      double[] dx=new double[sides],dy=new double[sides];


        double u;//unghi

        //desenam poligoanele in functie centru si impartirea pe unghiuri
        for(int i=0;i<sides;i++)
        {
            u=2*PI/sides*(i);
            dx[i]=cx+size*sin(u);
            dy[i]=cy-size*cos(u) ;

        }

        graphics_context.fillPolygon(dx,dy,sides);

           // }
        //catch ()
        System.out.println(size+" "+sides);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.getItems().addAll(
                "OVAL",
                    "REGULAR POLYGON"

        );
    }
}
